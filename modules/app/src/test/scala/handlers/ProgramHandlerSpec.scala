package com.eperinan.workshop.taglessfinal.app
package handlers

import cats.effect.{IO, Sync}
import cats.syntax.applicative._
import com.eperinan.workshop.taglessfinal.app.dto.Player.{Player, ResultStatsPlayer}
import com.eperinan.workshop.taglessfinal.common.handlers.LoggerHandler
import com.eperinan.workshop.taglessfinal.app.handlers.Common._
import io.chrisdavenport.log4cats.SelfAwareStructuredLogger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import org.http4s.client.Client
import org.http4s.client.blaze.Http1Client
import org.scalatest.prop.Checkers
import org.scalatest.{FunSuite, Matchers}

class ProgramHandlerSpec extends FunSuite with Matchers with Checkers {

  implicit def unsafeLogger[F[_]: Sync]: SelfAwareStructuredLogger[F] = Slf4jLogger.unsafeCreate[F]
  implicit val logger4CatsIOHandler                                   = new LoggerHandler[IO]
  implicit val client: Client[IO]                                     = Http1Client[IO]().unsafeRunSync

  test("start works as expected when the fetch returns StatsPlayer") {

    check {

      implicit val _ = new ForniteAPIHandler[IO] {
        override def fetchStats(player: Player): IO[ResultStatsPlayer] = resultStatsOk.pure[IO]
        override def displayStats(stats: ResultStatsPlayer): IO[String] =
          stats.fold(err => err.throwable.getMessage, stats => stats.stats.p2.score.field).pure[IO]
      }

      val programHandler = new ProgramHandler[IO]

      val result: String = programHandler.start(playerOk).unsafeRunSync()

      result == resultStatsOk.fold(
        err => err.throwable.getMessage,
        stats => stats.stats.p2.score.field)
    }

  }

  test("start works as expected when the fetch returns ErrorStatsPlayer") {

    check {

      implicit val _ = new ForniteAPIHandler[IO] {
        override def fetchStats(player: Player): IO[ResultStatsPlayer] = resultStatsKO.pure[IO]
        override def displayStats(stats: ResultStatsPlayer): IO[String] =
          stats.fold(err => err.throwable.getMessage, stats => stats.stats.p2.score.field).pure[IO]
      }

      val programHandler = new ProgramHandler[IO]

      val result: String = programHandler.start(playerWrongParse).unsafeRunSync()

      result == resultStatsKO.fold(
        err => err.throwable.getMessage,
        stats => stats.stats.p2.score.field)
    }

  }
}

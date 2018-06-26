package com.eperinan.workshop.taglessfinal.app
package handlers

import cats.effect.{IO, Sync}
import com.eperinan.workshop.taglessfinal.app.dto.Player._
import com.eperinan.workshop.taglessfinal.common.handlers.LoggerHandler
import com.eperinan.workshop.taglessfinal.app.handlers.Common._
import io.chrisdavenport.log4cats.SelfAwareStructuredLogger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import org.http4s.client.Client
import org.http4s.client.blaze.Http1Client
import org.scalatest.prop.Checkers
import org.scalatest.{FunSuite, Matchers}

class ForniteAPIHandlerSpec extends FunSuite with Matchers with Checkers {

  implicit def unsafeLogger[F[_]: Sync]: SelfAwareStructuredLogger[F] = Slf4jLogger.unsafeCreate[F]
  implicit val logger4CatsIOHandler                                   = new LoggerHandler[IO]
  implicit val client: Client[IO]                                     = Http1Client[IO]().unsafeRunSync
  val forniteApiHandler                                               = new ForniteAPIHandler[IO]

  test("fetchStats raises an error due to fail parsing the player") {

    check {
      val result: ResultStatsPlayer = forniteApiHandler.fetchStats(playerWrongParse).unsafeRunSync()
      result.isLeft && result.fold[Boolean](err => err.player == playerWrongParse, _ => false)
    }

  }

  test("fetchStats raises an error when the player is not found") {

    check {
      val result: ResultStatsPlayer = forniteApiHandler.fetchStats(playerNotFound).unsafeRunSync()
      result.isLeft && result.fold[Boolean](err => err.player == playerNotFound, _ => false)
    }
  }

  test("fetchStats returns the stats when the player is ok") {

    check {
      val result: ResultStatsPlayer = forniteApiHandler.fetchStats(playerOk).unsafeRunSync()
      result.isRight && result
        .fold[Boolean](_ => false, statsPlayer => statsPlayer.stats.p2.isInstanceOf[StatsP])
    }
  }

  test("displayStats displays the stats as expected") {

    check {
      val result: String = forniteApiHandler.displayStats(resultStatsOk).unsafeRunSync()
      result == s"Player: ${statsOk.epicUserHandle} [${statsOk.platformName}] - Score ${statsOk.stats.p2.score.category} ${statsOk.stats.p2.score.displayValue} - ${statsOk.stats.p2.top1.label} ${statsOk.stats.p2.top1.field} ${statsOk.stats.p2.top1.displayValue}"

    }

  }

  test("displayStats displays the error as expected") {

    check {
      val result: String = forniteApiHandler.displayStats(resultStatsKO).unsafeRunSync()
      result == s"Something was wrong for the player ${statsKO.player.epicUserHandle} and I can't display the information. The error was ${statsKO.throwable.getMessage}"

    }

  }
}

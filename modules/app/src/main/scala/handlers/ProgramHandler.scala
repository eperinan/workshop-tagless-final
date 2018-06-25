package com.eperinan.workshop.taglessfinal.app
package handlers

import cats.MonadError
import cats.effect.Sync
import cats.syntax.applicative._
import cats.syntax.either._
import cats.syntax.functor._
import cats.syntax.flatMap._
import com.eperinan.workshop.taglessfinal.app.algebras.{ForniteAPI, Program}
import com.eperinan.workshop.taglessfinal.app.dto.Player._
import com.eperinan.workshop.taglessfinal.common.algebras.Logger
import com.eperinan.workshop.taglessfinal.common.handlers.LoggerHandler
import io.circe.generic.auto._
import org.http4s.circe._
import org.http4s.client.Client
import org.http4s._
import org.http4s.circe.jsonOf

class ProgramHandler[F[_]: Sync](
    implicit ME: MonadError[F, Throwable],
    log: LoggerHandler[F],
    fapi: ForniteAPI[F])
    extends Program[F] {

  override val logger: Logger[F]         = log
  override val forniteAPI: ForniteAPI[F] = fapi

  override def start(): F[Unit] =
    for {
      //player <- Player("eperinan", "psn").pure[F]
      //player <- Player("\\/-()", "psn").pure[F]
      player <- Player("maaaikoool", "psn").pure[F]
      stats <- ME.handleErrorWith[ResultStatsPlayer](
        forniteAPI.fetchStats(player).map(_.asRight[ErrorStatsPlayer]))(
        e => ErrorStatsPlayer(e, player).asLeft[StatsPlayer].pure[F]
      )
      _ <- forniteAPI.displayStats(stats)
    } yield ()
}

class ForniteAPIHandler[F[_]: Sync](
    implicit ME: MonadError[F, Throwable],
    log: LoggerHandler[F],
    client: Client[F])
    extends ForniteAPI[F] {

  override val logger: Logger[F] = log

  implicit val _ = jsonOf[F, StatsPlayer]

  override def fetchStats(player: Player): F[StatsPlayer] =
    for {
      uri <- ME.fromEither[Uri](Uri.fromString(
        s"https://api.fortnitetracker.com/v1/profile/${player.platformName}/${player.epicUserHandle}"))
      request <- Request[F](
        method = Method.GET,
        //headers = Headers(List(Header("Wrong Header", "152433486-fc09-4907-a833-02d087928161"))),
        headers = Headers(List(Header("TRN-Api-Key", "152433486-fc09-4907-a833-02d087928161"))),
        uri = uri
      ).pure[F]
      stats <- client.fetch[StatsPlayer](request)(response => response.as[StatsPlayer])
    } yield stats

  override def displayStats(stats: ResultStatsPlayer): F[Unit] = stats match {
    case Left(err) =>
      log.info(
        s"Something was wrong for the player ${err.player.epicUserHandle} and I can't display the information. The error was ${err.throwable.getMessage}")
    case Right(sp) =>
      log.info(
        s"Player: ${sp.epicUserHandle} [${sp.platformName}] - Score ${sp.stats.p2.score.category} ${sp.stats.p2.score.displayValue} - ${sp.stats.p2.top1.label} ${sp.stats.p2.top1.field} ${sp.stats.p2.top1.displayValue}")
  }
}

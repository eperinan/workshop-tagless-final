package com.eperinan.workshop.taglessfinal.app
package handlers

import cats.MonadError
import cats.effect.Sync
import cats.syntax.functor._
import cats.syntax.flatMap._
import com.eperinan.workshop.taglessfinal.app.algebras.{ForniteAPI, Program}
import com.eperinan.workshop.taglessfinal.app.dto.Player._
import com.eperinan.workshop.taglessfinal.common.algebras.Logger
import com.eperinan.workshop.taglessfinal.common.handlers.LoggerHandler

class ProgramHandler[F[_]: Sync](
    implicit ME: MonadError[F, Throwable],
    log: LoggerHandler[F],
    fapi: ForniteAPI[F])
    extends Program[F] {

  override val logger: Logger[F]         = log
  override val forniteAPI: ForniteAPI[F] = fapi

  override def start(player: Player): F[String] =
    for {
      stats   <- forniteAPI.fetchStats(player)
      display <- forniteAPI.displayStats(stats)
    } yield display
}

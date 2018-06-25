package com.eperinan.workshop.taglessfinal.app
package algebras

import com.eperinan.workshop.taglessfinal.app.dto.Player._
import com.eperinan.workshop.taglessfinal.common.algebras.Logger

trait Program[F[_]] {

  val logger: Logger[F]
  val forniteAPI: ForniteAPI[F]

  def start(): F[Unit]

}

trait ForniteAPI[F[_]] {

  val logger: Logger[F]

  def fetchStats(player: Player): F[StatsPlayer]
  def displayStats(stats: ResultStatsPlayer): F[Unit]

}

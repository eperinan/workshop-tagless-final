package com.eperinan.workshop.taglessfinal.app
package dto

object Player {

  type ResultStatsPlayer = Either[ErrorStatsPlayer, StatsPlayer]

  sealed trait ErrorStatsPlayer                                          extends Product with Serializable
  final case class FetchStatsError(throwable: Throwable, player: Player) extends ErrorStatsPlayer

  final case class Player(epicUserHandle: String, platformName: String)

  final case class StatsValue(
      label: String,
      field: String,
      category: String,
      valueInt: Int,
      value: String,
      rank: Int,
      percentile: Float,
      displayValue: String)

  final case class StatsP(score: StatsValue, top1: StatsValue)

  final case class Stats(p2: StatsP)

  final case class StatsPlayer(
      message: Option[String],
      error: Option[String],
      accountId: String,
      platformId: Int,
      platformName: String,
      platformNameLong: String,
      epicUserHandle: String,
      stats: Stats)
}

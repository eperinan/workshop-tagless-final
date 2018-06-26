package com.eperinan.workshop.taglessfinal.app
package dto

object Player {

  type ResultStatsPlayer = Either[ErrorStatsPlayer, StatsPlayer]

  final case class ErrorStatsPlayer(throwable: Throwable, player: Player)

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

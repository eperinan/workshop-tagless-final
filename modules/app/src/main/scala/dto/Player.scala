package com.eperinan.workshop.taglessfinal.app
package dto

object Player {

  type ResultStatsPlayer = Either[ErrorStatsPlayer, StatsPlayer]

  case class ErrorStatsPlayer(throwable: Throwable, player: Player)

  case class Player(epicUserHandle: String, platformName: String)

  case class StatsValue(
      label: String,
      field: String,
      category: String,
      valueInt: Int,
      value: String,
      rank: Int,
      percentile: Float,
      displayValue: String)

  case class StatsP(score: StatsValue, top1: StatsValue)

  case class Stats(p2: StatsP)

  case class StatsPlayer(
      message: Option[String],
      error: Option[String],
      accountId: String,
      platformId: Int,
      platformName: String,
      platformNameLong: String,
      epicUserHandle: String,
      stats: Stats)
}

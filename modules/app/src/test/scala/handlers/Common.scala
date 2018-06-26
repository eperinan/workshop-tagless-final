package com.eperinan.workshop.taglessfinal.app
package handlers

import cats.syntax.either._
import com.eperinan.workshop.taglessfinal.app.dto.Player._

object Common {
  val playerNotFound: Player   = Player("eperinan", "psn")
  val playerWrongParse: Player = Player("\\/-()", "psn")
  val playerOk: Player         = Player("maaaikoool", "psn")

  val statsKO: ErrorStatsPlayer = ErrorStatsPlayer(new Throwable("Wrong parsing"), playerWrongParse)
  val statsOk: StatsPlayer = StatsPlayer(
    None,
    None,
    "accountId",
    2,
    "psn",
    "Play Station 4",
    "maaaikoool",
    Stats(
      StatsP(
        score = StatsValue("Score", "Score", "General", 94500, "94500", 283, 2.toFloat, "94,500"),
        top1 = StatsValue("Wins", "Top1", "Tops", 3, "3", 4, 22.toFloat, "4,500")
      ))
  )

  val resultStatsOk: ResultStatsPlayer = statsOk.asRight[ErrorStatsPlayer]
  val resultStatsKO: ResultStatsPlayer = statsKO.asLeft[StatsPlayer]

}

package com.eperinan.workshop.taglessfinal.app

import com.eperinan.workshop.taglessfinal.app.dto.Player.Player
import interpreters.ProgramInterpreters.programInterpreterIO

object workshopAppTaglessFinal extends App {
  val playerNotFound   = Player("eperinan", "psn")
  val playerWrongParse = Player("\\/-()", "psn")
  val playerOk         = Player("maaaikoool", "psn")
  programInterpreterIO.start(playerOk).unsafeRunSync()
}

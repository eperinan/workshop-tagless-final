package com.eperinan.workshop.taglessfinal.app

import interpreters.ProgramInterpreters.programInterpreterIO

object workshopAppTaglessFinal extends App {
  programInterpreterIO.start().unsafeRunSync()
}

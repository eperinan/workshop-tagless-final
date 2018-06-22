package com.eperinan.workshop.taglessfinal.app

import interpreters.ProgramInterpreters.programInterpreterIO

object workshopAppTaglessFinal extends App {
  val result = programInterpreterIO.helloWorld().unsafeRunSync()
  println(result)
}

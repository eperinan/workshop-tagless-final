package com.eperinan.workshop.taglessfinal
package app

import com.eperinan.workshop.taglessfinal.interpreters.ProgramInterpreters.programInterpreterIO

object eperinanApp extends App {
  val result = programInterpreterIO.helloWorld().unsafeRunSync()
  println(result)
}

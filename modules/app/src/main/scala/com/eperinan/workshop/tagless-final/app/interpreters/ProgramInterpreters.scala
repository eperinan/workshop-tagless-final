package com.eperinan.workshop.taglessfinal
package interpreters

import cats.effect.IO
import com.eperinan.workshop.taglessfinal.handlers.ProgramHandler

object ProgramInterpreters {

  val programInterpreterIO = new ProgramHandler[IO]
}


package com.eperinan.workshop.taglessfinal.app
package interpreters

import cats.effect.IO
import com.eperinan.workshop.taglessfinal.common.interpreters.loggerHandler.logger4CatsIOHandler
import handlers.ProgramHandler

object ProgramInterpreters {

  val programInterpreterIO = new ProgramHandler[IO]
}

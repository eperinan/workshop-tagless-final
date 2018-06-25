package com.eperinan.workshop.taglessfinal.app
package interpreters

import cats.effect.IO
import com.eperinan.workshop.taglessfinal.common.interpreters.loggerHandler.logger4CatsIOHandler
import handlers.{ForniteAPIHandler, ProgramHandler}
import org.http4s.client.blaze.Http1Client

object ProgramInterpreters {

  implicit val client     = Http1Client[IO]().unsafeRunSync
  implicit val forniteApi = new ForniteAPIHandler[IO]

  val programInterpreterIO = new ProgramHandler[IO]
}

package com.eperinan.workshop.taglessfinal.common
package interpreters

import cats.effect.{IO, Sync}
import com.eperinan.workshop.taglessfinal.common.handlers.LoggerHandler
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger

object loggerHandler {
  implicit def unsafeLogger[F[_]: Sync] = Slf4jLogger.unsafeCreate[F]
  implicit val logger4CatsIOHandler     = new LoggerHandler[IO]
}

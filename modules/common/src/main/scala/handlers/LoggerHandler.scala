package com.eperinan.workshop.taglessfinal.common
package handlers

import cats.Applicative
import com.eperinan.workshop.taglessfinal.common.algebras.Logger
import io.chrisdavenport.log4cats.{Logger => LoggerLog4Cats}

class LoggerHandler[F[_]: Applicative](implicit logger: LoggerLog4Cats[F]) extends Logger[F] {

  override def error(message: => String): F[Unit] = logger.error(message)

  override def error(t: Throwable)(message: => String): F[Unit] = logger.error(t)(message)

  override def warn(message: => String): F[Unit] = logger.warn(message)

  override def info(message: => String): F[Unit] = logger.info(message)

  override def debug(message: => String): F[Unit] = logger.debug(message)

  override def trace(message: => String): F[Unit] = logger.trace(message)
}

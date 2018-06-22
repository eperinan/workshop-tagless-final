package com.eperinan.workshop.taglessfinal.app
package handlers

import cats.Monad
import cats.syntax.applicative._
import cats.syntax.functor._
import cats.syntax.flatMap._
import com.eperinan.workshop.taglessfinal.app.algebras.Program
import com.eperinan.workshop.taglessfinal.common.algebras.Logger
import com.eperinan.workshop.taglessfinal.common.handlers.LoggerHandler

class ProgramHandler[F[_]: Monad](implicit log: LoggerHandler[F]) extends Program[F] {
  override def helloWorld(): F[String] =
    for {
      _       <- logger.info("Hello World")
      message <- "Hello world".pure[F]
    } yield message

  override val logger: Logger[F] = log
}

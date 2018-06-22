package com.eperinan.workshop.taglessfinal
package handlers

import cats.Applicative
import cats.syntax.applicative._
import com.eperinan.workshop.taglessfinal.algebras.Program

class ProgramHandler[F[_]: Applicative] extends Program[F] {
  override def helloWorld(): F[String] = "Hello world".pure[F]
}

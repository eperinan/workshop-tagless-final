package com.eperinan.workshop.taglessfinal
package algebras

trait Program[F[_]] {
  def helloWorld(): F[String]
}
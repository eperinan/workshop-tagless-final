package com.eperinan.workshop.taglessfinal.app
package algebras

import com.eperinan.workshop.taglessfinal.common.algebras.Logger

trait Program[F[_]] {

  val logger: Logger[F]

  def helloWorld(): F[String]

}

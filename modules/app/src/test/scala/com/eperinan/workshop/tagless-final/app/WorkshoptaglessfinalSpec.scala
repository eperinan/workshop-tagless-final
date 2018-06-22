package com.eperinan.workshop.tagless-final
package app

import org.scalatest.{Matchers, WordSpecLike}

final class AppSpec extends WordSpecLike with Matchers {

  "DummyTest" should {
    "DummyTest must be true" in {
      1 shouldBe 1
    }
  }
}

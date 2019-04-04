package com.perseus

import org.scalatest.{BeforeAndAfterEach, FunSpec, Matchers}
import TestUtils._
import cats.Monad
import cats.implicits._
import cats.data.StateT._
import cats.syntax.flatMap._
import cats.syntax.functor._
import freestyle.tagless.effects.error.ErrorM
import freestyle.tagless.logging.LoggingM


abstract class UserServiceTest[F[_]](implicit E: ErrorM[F], L: LoggingM[F]) extends FunSpec with Matchers with BeforeAndAfterEach {

  implicit val repo: UserRepository[Test] = new InMemeoryUserRepository


  describe("GetUser") {
    it("should get the required user Details") {
      UserService[Test].getUser(1)
    }
  }

}

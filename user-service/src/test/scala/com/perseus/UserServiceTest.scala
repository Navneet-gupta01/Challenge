package com.perseus

import org.scalatest.{FunSpec, Matchers}
import TestUtils._
import cats.Monad
import cats.implicits._
import cats.data.StateT._
import cats.syntax.flatMap._
import cats.syntax.functor._
import freestyle.tagless.logging.LoggingM


class UserServiceTest extends FunSpec with Matchers {
//  implicit val repo: UserRepository[Test] = new InMemeoryUserRepository
//
//  implicit val log: LoggingM[Test] = new ConsoleLogging
//
//  describe("GetUser") {
//    it("should get the required user Details") {
//      UserService[Test].getUser(1)
//    }
//  }

}

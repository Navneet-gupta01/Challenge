package com.perseus

import TestUtils._
import cats.implicits._

class InMemoryUserRepositoryTest extends UserRepositoryTest[Test] {
  implicit var state : TestState = _

  override def createUserRepositoryWith(users: List[UserEntity]): UserRepository[Test] = {
    state = TestState(
      users)
    new InMemeoryUserRepository
  }

  override def run[A](toBeExecuted: Test[A]): Either[UserDomainErrors, A] = toBeExecuted.runA(state)
}

package com.perseus

import cats.data.StateT

object TestUtils {
  case class TestState(users: List[UserEntity])

  type OrError[A] = Either[UserDomainErrors, A]
  type Test[A] = StateT[OrError, TestState, A]
}

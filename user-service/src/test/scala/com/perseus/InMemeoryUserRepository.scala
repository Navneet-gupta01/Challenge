package com.perseus

import TestUtils._
import cats.data.StateT
import cats.data.StateT._
import cats.implicits._

class InMemeoryUserRepository extends UserRepository.Handler[Test] {
  override def reset: Test[Int] = for {
    state  <- StateT.get[OrError, TestState]
    _ <- modify[OrError, TestState](state => state.copy(users = Nil:List[UserEntity]))
    result <- pure[OrError,TestState, Int](1)
  } yield result

  override def list: Test[List[UserEntity]] = for {
    state <- StateT.get[OrError, TestState]
    result <- pure[OrError, TestState, List[UserEntity]](state.users)
  } yield result

  override def listSubscribed: Test[List[UserEntity]] = for {
    state <- StateT.get[OrError, TestState]
    result <- pure[OrError, TestState, List[UserEntity]](state.users.filter(_.subscribedtonewsletter))  //foldLeft(Nil:List[UserEntity])((a,b) => if(b.subscribedtonewsletter) b::a else a))
  } yield result

  override def getUser(id: Long): Test[Option[UserEntity]] = for {
    state <- StateT.get[OrError, TestState]
    result <- state.users.find(_.id === id.some) match {
      case Some(x) =>  pure[OrError, TestState, Option[UserEntity]](x.some)
      case _  => pure[OrError, TestState, Option[UserEntity]](none[UserEntity])
    }
  } yield result
}

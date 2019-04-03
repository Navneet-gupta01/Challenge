package com.perseus

import cats.Monad
import freestyle.tagless._
import freestyle.tagless.effects._
import cats.data.Validated._
import cats.data._
import cats.implicits._
import freestyle.tagless.effects.error.ErrorM
import freestyle.tagless.logging.LoggingM
import freestyle.tagless.module

@module
trait UserService[F[_]] {
  implicit val M: Monad[F]
  implicit val L: LoggingM[F]

  val model = classOf[UserEntity].getSimpleName
  val error: ErrorM[F]
  val repo: UserRepository[F]

  def getUser(id: Long): F[Option[UserEntity]] =
    for {
      _ <- L.info(s"Fetching User for user-id: ${id}")
      userEntity <- repo.getUser(id)
      _ <- error.either[UserEntity](userEntity.toRight(UserDoesNotExist(id)))
    } yield userEntity

  def listUsers: F[List[UserEntity]] =
    for {
      _ <- L.info("Trying to Fetch list of users")
      userEntities <- repo.list
    } yield userEntities

  def listSubscribedUsers: F[List[UserEntity]] =
    for {
      _ <- L.info("Trying to Fetch list of users")
      userEntities <- repo.listSubscribed
    } yield userEntities
  def reset: F[Int] = for {
    _ <- L.info("REsetting user Table")
    reset <- repo.reset
  } yield reset
}



package com.perseus

import cats.Monad
import doobie.implicits._
import doobie.util.transactor.Transactor

class UserRepositoryHandler[F[_]: Monad](implicit T: Transactor[F]) extends UserRepository.Handler[F] {
  import UserQueries._

  override def reset: F[Int] =
    dropQuery
    .run
    .flatMap(drops =>
      createQuery
        .run
        .map(_ + drops))
    .transact(T)

  override def list: F[List[UserEntity]] =
    listQuery
      .to[List]
      .transact(T)

  override def listSubscribed: F[List[UserEntity]] =
    listSubscribedQuery
      .to[List]
      .transact(T)

  override def getUser(id: Long): F[Option[UserEntity]] =
    getQuery(id)
      .option
      .transact(T)
}

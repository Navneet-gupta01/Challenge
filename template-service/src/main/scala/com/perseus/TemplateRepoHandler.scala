package com.perseus

import cats.Monad
import doobie.util.transactor.Transactor
import doobie.implicits._

class TemplateRepoHandler[F[_]: Monad](implicit T: Transactor[F]) extends TemplateRepo.Handler[F]{
  import TemplateQueries._

  override def reset: F[Int] =
    dropQuery
      .run
    .flatMap(drops =>
      createQuery
        .run
        .map(_ + drops))
    .transact(T)

  override def getTemplateById(id: Long): F[Option[TemplateEntity]] =
    getQuery(id)
      .option
      .transact(T)

  override def getTemplateByKey(key: String): F[Option[TemplateEntity]] =
    getByKeyQuery(key)
      .option
      .transact(T)


}

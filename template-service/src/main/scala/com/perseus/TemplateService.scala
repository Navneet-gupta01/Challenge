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
trait TemplateService[F[_]] {
  implicit val M: Monad[F]
  implicit val L: LoggingM[F]

  val model = classOf[TemplateEntity].getSimpleName
  val error: ErrorM[F]
  val repo: TemplateRepo[F]

  def getById(id: Long) : F[Option[TemplateEntity]] =
    for {
      _ <- L.info("Fetching template by id: $id")
      template <- repo.getTemplateById(id)
      _ <- error.either[TemplateEntity](template.toRight(TemplateDoesNotExistForId(id)))
    } yield template

  def getByKey(key:String): F[Option[TemplateEntity]] =
    for {
      _ <- L.info("Fetching template by key: $key")
         template <- repo.getTemplateByKey(key)
         _ <- error.either[TemplateEntity](template.toRight(TemplateDoesNotExistForKey(key)))
    } yield template

  def reset: F[Int] = for {
    _ <- L.info("REsetting user Table")
    reset <- repo.reset
  } yield reset
}

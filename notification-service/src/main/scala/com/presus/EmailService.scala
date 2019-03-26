package com.presus

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
trait EmailService[F[_]] {

  implicit val M: Monad[F]
  implicit val L: LoggingM[F]

  def sendMessages(messages: List[String]) : F[Int] =
    for {
      _ <- L.info(s"Sending Messages : ${messages}")
      sent <- messages.length.pure[F]
    } yield sent
}

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
  val error: ErrorM[F]


  def sendWelcomeMessages(message: String) : F[Int] =
    for {
      _ <- L.info(s"Sending Messages : ${message}")
      sent <- 1.pure[F]
    } yield sent

  def sendNewsletterMessages(messages: List[String]) : F[Int] =
    for {
      _ <- L.info(s"Sending Messages : ${messages}")
      sent <- if(messages.length >=10)
        messages.length.pure[F] //send message
      else 0.pure[F]      // donot send message since no sufficient users found subscribed
    } yield sent
}

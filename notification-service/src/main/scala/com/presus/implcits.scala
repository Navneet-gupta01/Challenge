package com.presus

import cats.effect.IO
import cats.Monad
import cats.effect.{ContextShift, IO}

object implicits extends ErrorHandlerImplicits

trait ErrorHandlerImplicits{
  import com.olegpy.meow.hierarchy._
  implicit def notiHttpErrorHandler: HttpErrorHandler[IO, NotiDomainErrors] = new NotiHttpErrorHandler[IO]
}

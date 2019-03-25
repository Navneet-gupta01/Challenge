package com.presus


import cats.effect.IO
import cats.effect.{ConcurrentEffect, ExitCode, IOApp}
import doobie.util.transactor.Transactor
import freestyle.tagless.effects.error.implicits._
import freestyle.tagless.loggingJVM.log4s.implicits._
import freestyle.tagless.module
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._


@module
trait AppDep[F[_]] {
  val msgService: EmailService[F]
}

object NotiApp extends IOApp {
  import cats.implicits._

  def run(args: List[String]): IO[ExitCode] = bootstrap[IO]

  def bootstrap[F[_] : ConcurrentEffect](implicit api: NotiApi[F], app: AppDep[F]): F[ExitCode] = {
    val services = api.endPoints

    BlazeServerBuilder[F]
      .bindHttp(8085, "localhost")
      .withHttpApp(Router("/" -> services).orNotFound)
      .serve.compile.drain.as(ExitCode.Success)

  }
}

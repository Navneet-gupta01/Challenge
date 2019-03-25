package com.perseus

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
  val userService: TemplateService[F]
  val userRepository: TemplateRepo[F]
}
object TemplateApp extends IOApp {
  import cats.implicits._
  import com.olegpy.meow.hierarchy._
  import implicits._

  def run(args: List[String]): IO[ExitCode] = bootstrap[IO]

  def bootstrap[F[_] : ConcurrentEffect](implicit T: Transactor[F], api: TemplateApi[F], app: AppDep[F]): F[ExitCode] = {
    val services = api.routes

    BlazeServerBuilder[F]
      .bindHttp(8083, "localhost")
      .withHttpApp(Router("/" -> services).orNotFound)
      .serve.compile.drain.as(ExitCode.Success)

  }

}

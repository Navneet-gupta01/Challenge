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
  val userService: UserService[F]
  val userRepository: UserRepository[F]
}


object UserApp extends IOApp {
  import cats.implicits._
  import com.olegpy.meow.hierarchy._
  import implicits._

  def run(args: List[String]): IO[ExitCode] = bootstrap[IO]

  def bootstrap[F[_] : ConcurrentEffect](implicit T: Transactor[F], api: UserApi[F], app: AppDep[F]): F[ExitCode] = {
    val services = api.routes

    BlazeServerBuilder[F]
      .bindHttp(8083, "0.0.0.0")
      .withHttpApp(Router("/" -> services).orNotFound)
      .serve.compile.drain.as(ExitCode.Success)

  }

}

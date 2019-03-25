package com.perseus

import cats.effect.Effect
import cats.implicits._
import freestyle.tagless.logging.LoggingM
import org.http4s.HttpRoutes
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class UserApi[F[_]: Effect] (implicit services: UserService[F],
                             log: LoggingM[F],
                             H: HttpErrorHandler[F, UserDomainErrors]) extends Http4sDsl[F] {
  import Codecs._

  val endPoints = HttpRoutes.of[F] {
    case GET -> Root / "users" /  LongVar(id)  =>
      services.getUser(id) flatMap { item =>
        Ok(item.asJson)
      }

    case GET -> Root / "users" =>
      services.listUsers flatMap { item =>
        Ok(item.asJson)
      }

    case POST -> Root / "users" =>
      services.reset flatMap { item =>
        Ok(item.asJson)
      }
  }

  val routes: HttpRoutes[F] = H.handle(endPoints)
}


object UserApi {
  implicit def instance[F[_]:Effect](implicit services: UserService[F],
                                     log: LoggingM[F],
                                     H: HttpErrorHandler[F, UserDomainErrors]) : UserApi[F] = new UserApi[F]
}
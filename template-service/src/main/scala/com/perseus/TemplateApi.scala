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

class TemplateApi[F[_]: Effect] (implicit services: TemplateService[F],
                                 log: LoggingM[F],
                                 H: HttpErrorHandler[F, TemplateDomainErrors]) extends Http4sDsl[F] {

  val endPoints = HttpRoutes.of[F] {
    case GET -> Root / "templates" /  LongVar(id)  =>
      services.getById(id) flatMap { item =>
        Ok(item.asJson)
      }

    case GET -> Root / "templates"/ key =>
      services.getByKey(key) flatMap { item =>
        Ok(item.asJson)
      }

    case POST -> Root / "templates" =>
      services.reset flatMap { item =>
        Ok(item.asJson)
      }
  }

  val routes: HttpRoutes[F] = H.handle(endPoints)
}


object TemplateApi {
  implicit def instance[F[_]:Effect](implicit services: TemplateService[F],
                                     log: LoggingM[F],
                                     H: HttpErrorHandler[F, TemplateDomainErrors]) : TemplateApi[F] = new TemplateApi[F]
}

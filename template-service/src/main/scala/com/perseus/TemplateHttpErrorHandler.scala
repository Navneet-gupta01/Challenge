package com.perseus

import cats.{Monad, MonadError}
import cats.implicits._
import org.http4s.{HttpRoutes, Response}
import org.http4s.dsl.Http4sDsl
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe._

class TemplateHttpErrorHandler[F[_] : MonadError[?[_], TemplateDomainErrors]](implicit M: Monad[F]) extends HttpErrorHandler[F, TemplateDomainErrors] with Http4sDsl[F] {
  private val handler: TemplateDomainErrors => F[Response[F]] = {
    case a: TemplateDomainErrors => UnprocessableEntity(ErrorResp(a.errorMsg).asJson)
  }

  override def handle(routes: HttpRoutes[F]): HttpRoutes[F] =
    RoutesHttpErrorHandler(routes)(handler)

}

package com.presus

import cats.{Monad, MonadError}
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.{HttpRoutes, Response}

class NotiHttpErrorHandler[F[_] : MonadError[?[_], NotiDomainErrors]](implicit M: Monad[F]) extends HttpErrorHandler[F, NotiDomainErrors] with Http4sDsl[F] {
  private val handler: NotiDomainErrors => F[Response[F]] = {
    case a: NotiDomainErrors => UnprocessableEntity(ErrorResp(a.msg).asJson)
  }

  override def handle(routes: HttpRoutes[F]): HttpRoutes[F] =
    RoutesHttpErrorHandler(routes)(handler)

}

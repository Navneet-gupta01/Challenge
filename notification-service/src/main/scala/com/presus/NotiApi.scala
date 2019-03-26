package com.presus

import cats.effect.{ConcurrentEffect, Effect}
import cats.implicits._
import freestyle.tagless.effects.error
import freestyle.tagless.effects.error.ErrorM
import freestyle.tagless.logging.LoggingM
import org.http4s.HttpRoutes
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.dsl.Http4sDsl
import org.http4s.dsl.impl.LongVar
import org.http4s.dsl.io.{GET, Ok, Root}

import scala.concurrent.ExecutionContext.Implicits.global

class NotiApi[F[_]: ConcurrentEffect](
    implicit log: LoggingM[F],
    service: EmailService[F],
    error: ErrorM[F],
    H: HttpErrorHandler[F, NotiDomainErrors])
    extends Http4sDsl[F] {
  import Codecs._
  def getUserDetail(id: Long): F[UserEntity] =
    BlazeClientBuilder[F](global).resource.use { client =>
      val uri = Uri.uri("http://localhost:8083/users/") / id.toString
      client.expect[UserEntity](uri)
    }

  def getUsersList: F[List[UserEntity]] =
    BlazeClientBuilder[F](global).resource.use { client =>
      val uri = Uri.uri("http://localhost:8083/users/subscribed")
      client.expect[List[UserEntity]](uri)
    }

  def getTemplate(key: String): F[TemplateEntity] =
    BlazeClientBuilder[F](global).resource.use { client =>
      val uri = Uri.uri("http://localhost:8084/templates/") / key
      client.expect[TemplateEntity](uri)
    }

  def getSalutation(gender: String): String = gender match {
    case "male"   => "Mr."
    case "female" => "Mrs."
    case _        => "Dear"
  }

  def getUserName(userEntity: UserEntity): String =
    s"${userEntity.firstname} ${userEntity.surname}"

  val routes = HttpRoutes.of[F] {
    case GET -> Root / "welcome" / "users" / LongVar(id) =>
      for {
        _ <- log.info(s"Sending welcome template message to user wiht id: $id")
        user <- getUserDetail(id)
        _ <- log.info(s"Got User for welcome template message to with name: ${user.firstname}")
        templateEntity <- getTemplate("welcome")
        _ <- log.info(s"Got Template for Welcome key ${templateEntity.template}")
        sentNumbers <- service.sendMessages(
            List(
            templateEntity.template
              .replace("{{user.salutation}}", getSalutation(user.gender))
              .replace("{{user.name}}", getUserName(user))
              .replace("{{user.identifier}}", user.id.get + "")))
        res <- Ok(sentNumbers.asJson)
      } yield res
    case GET -> Root / "newsletter" =>
      for {
        _ <- log.info(s"Sending newsletter template message")
        users <- getUsersList
        _ <- error.either[Boolean]((if(users.length < 10) none[Boolean] else true.some).toRight(BadgeUpdateConstraintFailed(users.length)))
        _ <- log.info(s"got ${users.length} number of USers")
        templateEntity <- getTemplate("newsletter")
        sentNumbers <- service.sendMessages(
          users.map(
            user =>
              templateEntity.template
                .replace("{{user.salutation}}", getSalutation(user.gender))
                .replace("{{user.name}}", getUserName(user))
                .replace("{{user.identifier}}", user.id.get + "")))
        res <- Ok(sentNumbers.asJson)
      } yield res
  }

  val endPoints = H.handle(routes)

}

object NotiApi {
  implicit def instance[F[_]: ConcurrentEffect](implicit log: LoggingM[F],
                                                service: EmailService[F],error: ErrorM[F],H: HttpErrorHandler[F, NotiDomainErrors]): NotiApi[F] = new NotiApi[F]
}
package com.presus

import cats.Applicative
import cats.effect.Sync
import io.circe.generic.auto._
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

object Codecs {
  implicit def templateEntityEncoder[F[_] : Applicative]: EntityEncoder[F, TemplateEntity] = jsonEncoderOf[F, TemplateEntity]

  implicit def templateEntityDecoder[F[_] : Sync]: EntityDecoder[F, TemplateEntity] = jsonOf[F, TemplateEntity]
  implicit def usersEntityEncoder[F[_] : Applicative]: EntityEncoder[F, UserEntity] = jsonEncoderOf[F, UserEntity]

  implicit def usersEntityDecoder[F[_] : Sync]: EntityDecoder[F, UserEntity] = jsonOf[F, UserEntity]

  implicit def usersListEntityEncoder[F[_] : Applicative]: EntityEncoder[F, List[UserEntity]] = jsonEncoderOf[F, List[UserEntity]]

  implicit def usersListEntityDecoder[F[_] : Sync]: EntityDecoder[F, List[UserEntity]] = jsonOf[F, List[UserEntity]]
}

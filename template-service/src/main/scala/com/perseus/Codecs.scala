package com.perseus

import cats.Applicative
import cats.effect.Sync
import io.circe.generic.auto._
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

object Codecs {
  implicit def errorRespEncoder[F[_] : Sync]: EntityEncoder[F, ErrorResp] = jsonEncoderOf[F, ErrorResp]

  implicit def errorRespDecoder[F[_] : Sync]: EntityDecoder[F, ErrorResp] = jsonOf[F, ErrorResp]

  implicit def templateEntityEncoder[F[_] : Applicative]: EntityEncoder[F, TemplateEntity] = jsonEncoderOf[F, TemplateEntity]

  implicit def templateEntityDecoder[F[_] : Sync]: EntityDecoder[F, TemplateEntity] = jsonOf[F, TemplateEntity]
}

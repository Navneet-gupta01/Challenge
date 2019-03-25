package com.perseus

import cats.Applicative
import cats.effect.Sync
import io.circe.generic.auto._
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}

object Codecs {
  implicit def errorRespEncoder[F[_] : Sync]: EntityEncoder[F, ErrorResp] = jsonEncoderOf[F, ErrorResp]

  implicit def errorRespDecoder[F[_] : Sync]: EntityDecoder[F, ErrorResp] = jsonOf[F, ErrorResp]

  implicit def accountEntityEncoder[F[_] : Applicative]: EntityEncoder[F, UserEntity] = jsonEncoderOf[F, UserEntity]

  implicit def accountEntityDecoder[F[_] : Sync]: EntityDecoder[F, UserEntity] = jsonOf[F, UserEntity]
}

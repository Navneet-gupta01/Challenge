package com.perseus

import freestyle.tagless.tagless


@tagless(true)
trait TemplateRepo[F[_]] {
  def getTemplateById(id: Long): F[Option[TemplateEntity]]
  def getTemplateByKey(key :String): F[Option[TemplateEntity]]
  def reset: F[Int]
}

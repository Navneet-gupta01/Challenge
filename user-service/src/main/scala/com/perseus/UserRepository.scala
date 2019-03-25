package com.perseus

import freestyle.tagless.tagless

@tagless(true)
trait UserRepository[F[_]] {
  def reset: F[Int]
  def list:F[List[UserEntity]]
  def listSubscribed:F[List[UserEntity]]
  def getUser(id: Long): F[Option[UserEntity]]
}

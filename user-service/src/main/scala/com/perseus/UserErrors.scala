package com.perseus


sealed trait UserDomainErrors extends Exception{
  def errorMsg: String
}

case class UserDoesNotExist(user_id: Long) extends UserDomainErrors {
  override def errorMsg: String = s"User Not found for user_id: ${user_id}"
}
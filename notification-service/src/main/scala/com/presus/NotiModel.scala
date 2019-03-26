package com.presus

case class TemplateEntity(template:String, key: String ,id: Option[Long])
case class UserEntity(surname: String,firstname: String,gender: String, email: String,subscribedtonewsletter: Boolean, id: Option[Long])


sealed trait NotiDomainErrors extends Exception {
  def msg: String
}

case class BadgeUpdateConstraintFailed(user: Int) extends NotiDomainErrors {
  override def msg: String = s"Batch send Constriant Failed. Found Only ${user} users"

}
case class UserDoesNotExist(user_id: Long) extends NotiDomainErrors {
  override def msg: String = s"User Not found for user_id: ${user_id}"
}

final case class ErrorResp(errors: String)

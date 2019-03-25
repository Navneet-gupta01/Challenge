package com.presus

case class TemplateEntity(template:String, key: String ,id: Option[Long])
case class UserEntity(surname: String,firstname: String,gender: String, email: String,subscribedtonewsletter: Boolean, id: Option[Long])


sealed trait NotiDomainErrors extends Exception {
  def msg: String
}

case class BadgeUpdateConstraintFailed(user: Int) extends NotiDomainErrors {
  override def msg: String = s"Batch send Constriant Failed. Found Only ${user} users"

}


final case class ErrorResp(errors: String)


object ErrorResp {
  def apply(msg: String): ErrorResp = ErrorResp(msg)
}
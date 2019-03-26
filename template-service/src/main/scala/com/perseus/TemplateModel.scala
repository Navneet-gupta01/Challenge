package com.perseus

case class TemplateEntity(template:String, key: String ,id: Option[Long])


final case class ErrorResp(errors: String)

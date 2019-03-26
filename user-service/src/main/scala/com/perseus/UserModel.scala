package com.perseus


case class UserEntity(surname: String,firstname: String,gender: String, email: String,subscribedtonewsletter: Boolean, id: Option[Long])


final case class ErrorResp(errors: String)

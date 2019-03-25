package com.perseus

sealed trait TemplateDomainErrors extends Exception{
  def errorMsg: String
}

case class TemplateDoesNotExistForId(template_id: Long) extends TemplateDomainErrors {
  override def errorMsg: String = s"Template Not found for template_id: ${template_id}"
}

case class TemplateDoesNotExistForKey(template_key: String) extends TemplateDomainErrors {
  override def errorMsg: String = s"Template Not found for template_key: ${template_key}"
}
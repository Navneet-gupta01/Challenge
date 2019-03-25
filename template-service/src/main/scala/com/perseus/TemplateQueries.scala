package com.perseus

import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.update.Update0

object TemplateQueries {
  def getQuery(id: Long): Query0[TemplateEntity] =
    sql"""SELECT template, key, id from templates where id = $id""".query[TemplateEntity]

  def getByKeyQuery(key : String): Query0[TemplateEntity] =
    sql"""SELECT template, key, id from templates where key = $key""".query[TemplateEntity]

  def createQuery: Update0 =
    sql"""
         CREATE TABLE templates (
            id SERIAL NOT NULL PRIMARY KEY,
            template text NOT NULL,
            key VARCHAR(255) NOT NULL,
            UNIQUE (key)
         )
       """.update

  def dropQuery: Update0 =
    sql"""DROP TABLE IF EXISTS templates""".update
}

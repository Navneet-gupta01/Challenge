package com.perseus

import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.update.Update0

object UserQueries {
  def insertQuery(userEntity: UserEntity): Update0 =
    sql"""INSERT INTO users (surname, firstname, email, gender, subscribedtonewsletter) VALUES
          (${userEntity.surname},${userEntity.firstname},${userEntity.email},${userEntity.gender},${userEntity.subscribedtonewsletter})
       """.update

  def getQuery(id: Long): Query0[UserEntity] =
    sql"""SELECT surname, firstname, gender, email, subscribedtonewsletter, id from users where id = $id""".query[UserEntity]

  def listQuery: Query0[UserEntity] =
    sql"""SELECT surname, firstname, gender, email, subscribedtonewsletter, id from users""".query[UserEntity]
  def listSubscribedQuery: Query0[UserEntity] =
    sql"""SELECT surname, firstname, gender, email, subscribedtonewsletter, id from users where subscribedtonewsletter = true""".query[UserEntity]

  def createQuery: Update0 =
    sql"""
         CREATE TABLE users (
            id SERIAL NOT NULL PRIMARY KEY,
            surname VARCHAR(255) NOT NULL,
            email VARCHAR(255) NOT NULL,
            firstname VARCHAR(255) NOT NULL,
            gender VARCHAR(50) NOT NULL,
            subscribedtonewsletter boolean default false,
            UNIQUE (email)
         )
       """.update

  def dropQuery: Update0 =
    sql"""DROP TABLE IF EXISTS users""".update

}

package com.perseus

import java.util.Properties

import cats.Monad
import cats.effect.{ContextShift, IO}
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import doobie.hikari.HikariTransactor
import doobie.util.transactor.Transactor

import scala.concurrent.ExecutionContext

object implicits extends ExecutionContextImplict with DoobieImplicits with RepositoryImplicits with ErrorHandlerImplicit

trait DoobieImplicits {

  val config = new HikariConfig(new Properties {
    setProperty("driverClassName", "org.postgresql.Driver")
    setProperty("jdbcUrl", "jdbc:postgresql:perseus")
    setProperty("username", "postgres")
    setProperty("password", "postgres")
    setProperty("maximumPoolSize", "5")
    setProperty("minimumIdle", "3")
    setProperty("idleTimeout", "600000")
    setProperty("connectionTimeout", "30000")
    setProperty("connectionTestQuery", "SELECT 1")
    setProperty("maxLifetime", "1800000")
    setProperty("autoCommit", "true")
  })

  implicit def xa(implicit CT: ExecutionContext, TC: ExecutionContext, ev: ContextShift[IO]): HikariTransactor[IO] =
    HikariTransactor.apply[IO](new HikariDataSource(config), CT, TC)
}
trait ExecutionContextImplict {
  implicit val ec: ExecutionContext = ExecutionContext.Implicits.global
}

trait RepositoryImplicits {
  implicit def templateRepositoryHandler[F[_] : Monad](implicit T: Transactor[F]): TemplateRepo.Handler[F] =
    new TemplateRepoHandler[F]
}

trait ErrorHandlerImplicit {
  import com.olegpy.meow.hierarchy._

  implicit def templateHttpErrorHandler: HttpErrorHandler[IO, TemplateDomainErrors] = new TemplateHttpErrorHandler[IO]
}
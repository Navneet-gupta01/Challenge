package com.perseus

import cats.MonadError
import cats.syntax.flatMap._
import cats.syntax.functor._
import freestyle.tagless.tagless
import org.scalatest.{BeforeAndAfterEach, FunSpec, Matchers}


abstract class UserRepositoryTest[F[_]](implicit ME:MonadError[F, UserDomainErrors])
  extends FunSpec with Matchers with BeforeAndAfterEach {

  describe("list"){
    it("should list down all the users"){
      val sut = createUserRepositoryWith(
        List(UserEntity("Gupta" , "Navneet", "male", "navneet@navneet.com", false, Some(1L)),
          UserEntity("Kumar" , "Navneet", "male", "navneet1@navneet1.com", true, Some(2L))))
      run(sut.list) should be(
        Right(
          List(
            UserEntity("Gupta" , "Navneet", "male", "navneet@navneet.com", false, Some(1L)),
            UserEntity("Kumar" , "Navneet", "male", "navneet1@navneet1.com", true, Some(2L))
          )
        ))
    }

    it("should list down only subscribed users"){
      val sut = createUserRepositoryWith(
        List(UserEntity("Gupta" , "Navneet", "male", "navneet@navneet.com", false, Some(1L)),
          UserEntity("Kumar" , "Navneet", "male", "navneet1@navneet1.com", true, Some(2L))))
      run(sut.listSubscribed) should be(
        Right(
          List(
            UserEntity("Kumar" , "Navneet", "male", "navneet1@navneet1.com", true, Some(2L))
          )
        ))
    }

    describe("getUser") {
      it("should fetch the user by id"){
        val sut = createUserRepositoryWith(
          List(UserEntity("Gupta" , "Navneet", "male", "navneet@navneet.com", false, Some(1L)),
            UserEntity("Kumar" , "Navneet", "male", "navneet1@navneet1.com", true, Some(2L))))

        run(sut.getUser(1L)) should be(
          Right(Some(UserEntity("Gupta" , "Navneet", "male", "navneet@navneet.com", false, Some(1L))))
        )
      }

      it("should fetch the user by it should return None for non-existing user") {
        val sut = createUserRepositoryWith(
          List(UserEntity("Gupta" , "Navneet", "male", "navneet@navneet.com", false, Some(1L)),
            UserEntity("Kumar" , "Navneet", "male", "navneet1@navneet1.com", true, Some(2L))))

        run(sut.getUser(4L)) should be(
          Right(None)
        )
      }
    }

    describe("reset") {
      it("should reset the table") {
        val sut = createUserRepositoryWith(
          List(UserEntity("Gupta" , "Navneet", "male", "navneet@navneet.com", false, Some(1L)),
            UserEntity("Kumar" , "Navneet", "male", "navneet1@navneet1.com", true, Some(2L))))

        run(for {
          _ <- sut.reset
          resetedUsers <- sut.list
        } yield resetedUsers) should be (Right(List()))
      }
    }
  }

  def createUserRepositoryWith(users: List[UserEntity]): UserRepository[F]
  def run[A](toBeExecuted: F[A]) : Either[UserDomainErrors, A]
}

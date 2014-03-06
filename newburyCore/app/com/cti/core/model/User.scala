package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._

object User {
  
  case class User(id: Long,firstName: String, lastName: String, username: String, email: String, phoneId: Long, password: String) extends Entity
  
  class Users(tag: Tag) extends EntityTable[User](tag, "USER") {
    val firstName = column[String]("FIRST_NAME")  
    val lastName = column[String]("LAST_NAME")
    val username = column[String]("USERNAME")
    val email = column[String]("EMAIL")
    val phoneId = column[Long]("PHONE_ID")
    val password = column[String]("PASSWORD")
    def fullName = firstName.asColumnOf[String] + " " + lastName.asColumnOf[String]
    def * = (id, firstName, lastName, username, email, phoneId, password) <> (User.tupled, User.unapply) 
  }
  val users = TableQuery[Users]
}
package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._

object User {
  
  case class User(id: Long,firstName: String, lastName: String, username: String, email: String, phoneId: String, password: String) extends Entity
  
  class Users(tag: Tag) extends EntityTable[User](tag, "USER") {
    def firstName = column[String]("FIRST_NAME")  
    def lastName = column[String]("LAST_NAME")
    def username = column[String]("USERNAME")
    def email = column[String]("EMAIL")
    def phoneId = column[String]("PHONE_ID")
    def password = column[String]("PASSWORD")
    def * = (id, firstName, lastName, username, email, phoneId, password) <> (User.tupled, User.unapply) 
  }
  val users = TableQuery[Users]
}
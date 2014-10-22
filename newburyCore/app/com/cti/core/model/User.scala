package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._
import com.cti.core.model.Phone._
import com.cti.core.service.EntityExtensions._
object User {
  
  case class User(id: Option[Long] = None,uuid: String = java.util.UUID.randomUUID().toString(),firstName: String, lastName: String, username: String, email: String, phoneId: Option[Long] = None, password: String) extends Entity
  case class UserPhone(user: Option[User], phone: Option[Phone])
  
  class Users(tag: Tag) extends EntityTable[User](tag, "USER") {
    val firstName = column[String]("FIRST_NAME")  
    val lastName = column[String]("LAST_NAME")
    val username = column[String]("USERNAME")
    val email = column[String]("EMAIL")
    val phoneId = column[Long]("PHONE_ID", O.Nullable)
    val password = column[String]("PASSWORD")
    def phone = foreignKey("PHONE_ID", phoneId, phones)(_.id)  
    def fullName = firstName.asColumnOf[String] + " " + lastName.asColumnOf[String]
    
    def * = (id.?, uuid, firstName, lastName, username, email, phoneId.?, password) <> (User.tupled, User.unapply) 
  }
  val users = TableQuery[Users]
}
package com.cti.core.service


object UserService {
  
	import com.cti.core.implicits._
	import scala.slick.driver.MySQLDriver.simple._
	import com.cti.core.model.User._
	import com.cti.core.model.Phone._
	import com.cti.core.service.EntityExtensions._
	import com.cti.core.service.UserExtensions._

  def findAll(): List[User] = {
    db.withSession { implicit session =>
    	users.list
    }
  }
  
  def findById(id: Long): Option[User] = {
    db.withSession { implicit session =>
    	users.filterById(id).list.headOption
    }
  }

  def findByFirstName(firstName: String): List[User] = {
    db.withSession { implicit session => 
    	users.filterByFirstName(firstName).list
    }
  }
  
  def findByEmail(email: String): Option[User] = {
    db.withSession { implicit session => 
    	users.filterByEmail(email).list.headOption
    }
  }
  
  def findByIdWithPhone(id: Long): Option[UserPhone] = {
    db.withSession { implicit session => 
    	val join = for {
    		(user, phone) <- users leftJoin phones on (_.phoneId === _.id)
    	} yield (user, phone)
    	join.firstOption.map{case(u:User, p:Phone) => UserPhone(Some(u),Some(p))}
    }
  }
  
  def saveOrUpdateUserAndPhone(userPhone: UserPhone): UserPhone = {
      
    val rvphone: Option[Phone] = userPhone.phone match {
        case Some(p) =>{
          Some(PhoneService.saveOrUpdate(p))
        } 
        case None => {
          val nu: Option[Phone] = None
          nu
        }
      }
    
    val newUser: Option[User] = Some(userPhone.user.get.copy(phoneId = rvphone.get.id)) 
    val rvuser: Option[User] = newUser match {
        case Some(u) => {
          Some(saveOrUpdate(u))
        }
        case None => {
          val nu: Option[User] = None
          nu
        }
      }
      
      UserPhone(rvuser, rvphone)
  }
  def delete(id: Long) = {
    db.withTransaction { implicit session => 
    	users.filterById(id).delete
    }
  }
  def saveOrUpdate(user: User): User = {
    db.withTransaction { implicit session =>
    if(user.id.isDefined){
    	println("HELLO WE HAVE AN ID SHOULD UPDATE " + user.id.get)
    	users.filterById(user.id.get).update(user)
      } else {
        println("NO ID FOUND: " + user.id.isDefined)
        users += user
      }
    }
    db.withTransaction { implicit session =>
  		findByEmail(user.email).get
    }
  }
  
}

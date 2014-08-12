package com.cti.core.service
import scala.slick.driver.MySQLDriver.simple._

import com.cti.core.model.User._
import scala.slick.lifted.Query
import com.cti.core.service.EntityExtensions._

object UserExtensions {
	
	implicit class UserQueryExtensions (val q: Query[Users, User, Seq]){
		def filterByFirstName(firstName: Column[String]) : Query[Users, User, Seq] = q.filter(_.firstName === firstName).sortBy(_.firstName)
		def filterByLastName(lastName: Column[String]) : Query[Users, User, Seq] = q.filter(_.lastName === lastName).sortBy(_.lastName)
		def filterByUsernameName(username: Column[String]) : Query[Users, User, Seq] = q.filter(_.username === username)
		def filterByEmail(email: Column[String]) : Query[Users, User, Seq] = q.filter(_.email === email)
		
	}
}
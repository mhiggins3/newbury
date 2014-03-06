package com.cti.core.service
import scala.slick.driver.MySQLDriver.simple._

import com.cti.core.model.User._
import scala.slick.lifted.Query
import com.cti.core.service.EntityExtensions._

object UserExtensions {
	
	implicit class UserQueryExtensions (val q: Query[Users, User]){
		def findByFirstName(firstName: Column[String]) : Query[Users, User] = q.filter(_.firstName === firstName).sortBy(_.firstName);
		def findByLastName(lastName: Column[String]) : Query[Users, User] = q.filter(_.lastName === lastName).sortBy(_.lastName);
		def findByUsernameName(username: Column[String]) : Query[Users, User] = q.filter(_.username === username);
		def findByEmailName(email: Column[String]) : Query[Users, User] = q.filter(_.username === email);
	}
}
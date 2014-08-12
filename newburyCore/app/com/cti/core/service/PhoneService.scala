package com.cti.core.service

object PhoneService {
  
	import com.cti.core.implicits._
	import scala.slick.driver.MySQLDriver.simple._
	import com.cti.core.model.User._
	import com.cti.core.model.Phone._
	import com.cti.core.service.EntityExtensions._
	
	
	def findBySerialNumber(serialNumber: String): Option[Phone] = {
	  db withSession { implicit session => 
	  	phones.filter(_.serialNumber === serialNumber).list.headOption
	  }
	}
	
	def saveOrUpdate(phone: Phone): Phone = {
	    db withTransaction { implicit session =>
	    	if(phone.id.isDefined){
	    		phones.filterById(phone.id.get).update(phone)
	    	} else {
	    	phones += phone
	    	}
	  }
	  findBySerialNumber(phone.serialNumber.get).get
	}
}
package com.cti.core.service

import com.cti.core.model.Entity

object BeaconService {
  
  	import com.cti.core.implicits._
	import scala.slick.driver.MySQLDriver.simple._
	import com.cti.core.model.Beacon._
	import com.cti.core.service.EntityExtensions._
	import com.cti.core.service.BeaconExtensions._
	
	def withSession(f: Option[Entity] => Any) = {
  		db.withSession { implicit session =>
  			f
  		}
  	}
	
	def findAll(): List[Beacon] = {
	  //withSession({beacons.list}) 
    db.withSession { implicit session =>
    	beacons.list
    }
  }
  
  def findById(id: Long): Option[Beacon] = {
    db.withSession { implicit session =>
    	beacons.filterById(id).list.headOption
    }
  }

  def findBySerialNumber(serialNumber: String): Option[Beacon] = {
    db.withSession { implicit session => 
    	beacons.filterBySerialNumber(serialNumber).list.headOption
    }
  }
  def saveOrUpdate(beacon: Beacon): Beacon = {
    db.withTransaction { implicit session =>
      beacons.insertOrUpdate(beacon)
     
    }
    
  	db.withTransaction { implicit session =>
  findBySerialNumber(beacon.serialNumber).get
  	}
  
  }
    def delete(id: Long) = {
    db.withTransaction { implicit session => 
    	beacons.filterById(id).delete
    }
  }
}

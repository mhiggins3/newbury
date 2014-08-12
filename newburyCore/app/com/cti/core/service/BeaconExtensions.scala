package com.cti.core.service

import scala.slick.driver.MySQLDriver.simple._

import com.cti.core.model.Beacon._
import scala.slick.lifted.Query
import com.cti.core.service.EntityExtensions._

object BeaconExtensions {

  	implicit class UserQueryExtensions (val q: Query[Beacons, Beacon, Seq]){
		def filterBySerialNumber(serialNumber: Column[String]) : Query[Beacons, Beacon, Seq] = q.filter(_.serialNumber === serialNumber)		
	}
}
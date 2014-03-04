package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._

object Beacon {
	case class Beacon(id: Long, name: String) extends Entity

	class Beacons(tag: Tag) extends EntityTable[Beacon](tag, "BEACON") {
		def name = column[String]("NAME", O.NotNull)
		def * = (id, name) <> (Beacon.tupled, Beacon.unapply) 
	}

	val beacons = TableQuery[Beacons]
}
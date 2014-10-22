package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._

object Beacon {
	case class Beacon(id: Option[Long] = None, uuid: String = java.util.UUID.randomUUID().toString(), name: String, serialNumber: String) extends Entity

	class Beacons(tag: Tag) extends EntityTable[Beacon](tag, "BEACON") {
		def name = column[String]("NAME", O.NotNull)
		def serialNumber = column[String]("SERIAL_NUMBER", O.NotNull)
		def * = (id.?, uuid, name, serialNumber) <> (Beacon.tupled, Beacon.unapply) 
	}

	val beacons = TableQuery[Beacons]
}

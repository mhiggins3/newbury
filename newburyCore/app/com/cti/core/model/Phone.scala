package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._

object Phone {
  
  case class Phone(id: Option[Long] = None,uuid: String = java.util.UUID.randomUUID().toString(), serialNumber: Option[String])
  
  	class Phones(tag: Tag) extends EntityTable[Phone](tag, "PHONE") {
	  	val serialNumber = column[String]("SERIAL_NUMBER", O.NotNull)
	  	def * = (id.?, uuid, serialNumber.?) <> (Phone.tupled, Phone.unapply)
    }
  
	val phones = TableQuery[Phones]

}
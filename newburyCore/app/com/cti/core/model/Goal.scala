package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._
import Beacon._

object Goal {
	case class Goal(id: Long, warmClue: String, hotClue: String, foundMessage: String, nextClue: String, beaconId: Long) extends Entity

	class Goals(tag: Tag) extends EntityTable[Goal](tag, "GOAL") {
		//def id = column[Long]("ID", O.PrimaryKey, O.AutoInc, O.NotNull)
		def warmClue = column[String]("WARM_CLUE", O.NotNull)
		def hotClue = column[String]("HOT_CLUE", O.NotNull)
		def foundMessage = column[String]("FOUND_MESSAGE", O.NotNull)
		def nextClue = column[String]("NEXT_CLUE", O.NotNull)
		def beaconId = column[Long]("BEACON_ID", O.NotNull)
		def * = (id, warmClue, hotClue, foundMessage, nextClue, beaconId) <> (Goal.tupled, Goal.unapply) 
		def beacon = foreignKey("BEACON_ID", beaconId, beacons)(_.id)  
	}
	
	val goals = TableQuery[Goals]
}
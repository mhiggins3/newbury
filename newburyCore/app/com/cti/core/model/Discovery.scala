package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._
import com.cti.core.model.Beacon._
import com.cti.core.model.User._
import com.cti.core.model.Goal._

import java.sql.Date


object Discovery {
	case class Discovery(id: Option[Long] = None, time: Date, beaconId: Long, userId: Long, goalId: Long) extends Entity
	
	class Discoveries(tag: Tag) extends EntityTable[Discovery](tag, "DISCOVERY"){
	  def time = column[Date]("TIME", O.NotNull)
	  def beaconId = column[Long]("BEACON_ID", O.NotNull)
	  def userId = column[Long]("USER_ID", O.NotNull)
	  def goalId = column[Long]("GOAL_ID", O.NotNull)
	  def * = (id.?, time, beaconId, userId, goalId) <> (Discovery.tupled, Discovery.unapply)
	  def beacon = foreignKey("BEACON_ID", beaconId, beacons)(_.id)
	  def user = foreignKey("USER_ID", userId, users)(_.id)
	  def goal = foreignKey("GOAL_ID", goalId, goals)(_.id)
	}
	
	val discoveries = TableQuery[Discoveries];
	
}
package com.cti.core.model
import scala.slick.driver.MySQLDriver.simple._

abstract class EntityTable[E](tag: Tag, tableName: String) extends Table[E](tag, tableName){
	def id = column[Long]("ID", O.PrimaryKey, O.AutoInc, O.NotNull)
}

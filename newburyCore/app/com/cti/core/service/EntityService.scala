package com.cti.core.service
import scala.slick.driver.MySQLDriver.simple._
import com.cti.core.model.EntityTable
import com.cti.core.implicits._

/*
class EntityService[T <: EntityTable[_]](tableQuery: TableQuery[T]) {
	def findAll(): List[T] = {
    db.withSession { implicit session =>
    	tableQuery.list()
    }
}
*/
object EntityService {
	
   
}
  
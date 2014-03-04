package com.cti.core.service
import scala.slick.driver.MySQLDriver.simple._

import scala.slick.lifted.Query
import com.cti.core.model.EntityTable
import com.cti.core.model.Entity

object EntityHelpers{
  
	implicit class QueryExtensions[T,E](val q: Query[T,E]){
	  
	  def page(num: Int, pageSize: Int = 10): Query[T,E] = q.drop((num - 1) * pageSize).take(pageSize)
	}

	implicit class EntityQueryExtentions(val q: Query[EntityTable[Entity], Entity]){
	  def findById(id: Long): Query[EntityTable[Entity], Entity] = q.findById(id)
	  def findAll(): Query[EntityTable[Entity], Entity] = q.findAll
	}
}
package com.cti.core.service
import scala.slick.driver.MySQLDriver.simple._

import scala.slick.lifted.Query
import com.cti.core.model.EntityTable
import com.cti.core.model.Entity

object EntityExtensions{
  
	implicit class QueryExtensions[T,E](val q: Query[T,E]){
	  def page(num: Int, pageSize: Int = 10): Query[T,E] = q.drop((num - 1) * pageSize).take(pageSize)
	}

	implicit class EntityQueryExtentions[T <: EntityTable[E],E](val q: Query[T,E]){
	  def findById(id: Long): Query[T,E] = q.findById(id)	  
	  def findAll(): Query[EntityTable[Entity], Entity] = q.findAll
	}
}
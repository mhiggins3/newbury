package com.cti.core.service
import scala.slick.driver.MySQLDriver.simple._

import scala.slick.lifted.Query
import com.cti.core.model.EntityTable
import com.cti.core.model.Entity

object EntityExtensions{
  
	implicit class QueryExtensions[T,E](val q: Query[T,E,Seq]){
	  def page(num: Int, pageSize: Int = 10): Query[T,E,Seq] = q.drop((num - 1) * pageSize).take(pageSize)
	}

	implicit class EntityQueryExtentions[T <: EntityTable[E],E](val q: Query[T,E,Seq]){
	  def filterById(id: Long): Query[T,E,Seq] = q.filter(_.id === id)	  
	}
}
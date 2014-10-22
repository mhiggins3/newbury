package com.cti.core.service

object GameService {
	
	import com.cti.core.implicits._
	import scala.slick.driver.MySQLDriver.simple._
	import com.cti.core.model.Game._
	import com.cti.core.service.EntityExtensions._

	def findAll(): List[Game] = {
	  db.withSession{ implicit session => {
		  games.list
	  }
	  }
	}
	
	def findById(id: Long): Option[Game] = {
	  db.withSession { implicit session => {
	  	games.filterById(id).list.headOption
	  }
	  }
	}
	
	def saveOrUpdate(game: Game): Game = {
	  db.withSession { implicit session => {
	    games.insertOrUpdate(game)  
	    games.filterByUUID(game.uuid).list.headOption.get
	   
	  }
	}
	}
}
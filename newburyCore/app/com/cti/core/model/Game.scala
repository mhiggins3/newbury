package com.cti.core.model
import Goal._
import scala.slick.driver.MySQLDriver.simple._
import User._

object Game {
	case class Game(id: Option[Long] = None,uuid: String = java.util.UUID.randomUUID().toString(), name: String, startClue: String) extends Entity

	
	class Games(tag: Tag) extends EntityTable[Game](tag, "GAME") {
		def name = column[String]("NAME", O.NotNull)
		def startClue = column[String]("START_CLUE", O.NotNull)
		def goals = gameToGoals.filter(_.gameId === id).flatMap(_.goal) 
		def players = gameToPlayer.filter(_.gameId === id).flatMap(_.game)
		def * = (id.?, uuid, name, startClue) <> (Game.tupled, Game.unapply)
	}
	lazy val games = TableQuery[Games]

	class GameToGoal(tag: Tag) extends Table[(Long, Long)](tag, "GAME_GOAL"){
	  def gameId = column[Long]("GAME_ID", O.NotNull)
	  def goalId = column[Long]("GOAL_ID", O.NotNull)
	  def * = (gameId, goalId)
	  def game = foreignKey("GAME_ID", gameId, games)(game => game.id)
	  def goal = foreignKey("GOAL_ID", goalId, goals)(goal => goal.id)  
	}
	
	lazy val gameToGoals = TableQuery[GameToGoal]
	
	class GameToPlayer(tag: Tag) extends Table[(Long, Long)](tag, "GAME_PLAYER"){
	  def gameId = column[Long]("GAME_ID", O.NotNull)
	  def playerId = column[Long]("USER_ID", O.NotNull)
	  def * = (gameId, playerId)
	  def game = foreignKey("GAME_ID", gameId, games)(game => game.id)
	  def player = foreignKey("PLAYER_ID", playerId, users)(player => player.id)
	}
	lazy val gameToPlayer = TableQuery[GameToPlayer]
}


package com.cti.web.controllers

import play.api.db.DB
import play.api.Play.current
import play.api.mvc.{Action, Controller}
import scala.slick.driver.MySQLDriver.simple._
import com.cti.core.model.Goal._

object Application extends Controller {
  def index = Action {
    def db = Database.forDataSource(DB.getDataSource())
    val clueone = goals.filter(_.beaconId === 1l)
    db withSession {
      implicit session =>
    	println(clueone.list)
    }
    Ok(views.html.index("Newbury is fun with proximity !"))
  }
}

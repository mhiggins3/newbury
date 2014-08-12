package com.cti.core
import scala.slick.driver.MySQLDriver.simple._

package object implicits {
	//TODO make this configurable 
	implicit val db = Database.forName("DefaultDS")
}
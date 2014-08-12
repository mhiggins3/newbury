import sbt._
import Keys._
import play.Play.autoImport._
import PlayKeys._

object Common {
  val buildOrganization = "cti"
  val buildVersion      = "1.0.0"
  val buildScalaVersion = "2.11.1"

  val buildSettings =  Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}



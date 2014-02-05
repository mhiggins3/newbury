import sbt._
import Keys._

object BuildSettings {
  val buildOrganization = "cti"
  val buildVersion      = "1.0.0"
  val buildScalaVersion = "2.10.0"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object RootBuild extends Build {
  import BuildSettings._
  lazy val root = Project(id = "root", base = file("."), settings = buildSettings)
    .aggregate(newburyCore, newburyWeb)

  lazy val newburyCore = Project(id = "newburyCore", base = file("newburyCore"), settings = buildSettings)

  lazy val newburyWeb = Project(id = "newburyWeb", base = file("newburyWeb"), settings = buildSettings)
    .dependsOn(newburyCore)
}

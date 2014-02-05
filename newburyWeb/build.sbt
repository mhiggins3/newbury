import play.Project._

name := """newburyWeb"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.0", 
  "org.webjars" % "bootstrap" % "2.3.1",
  "net.sf.barcode4j" % "barcode4j" % "2.0"
)

playScalaSettings

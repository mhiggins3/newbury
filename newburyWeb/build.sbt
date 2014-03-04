import play.Project._

name := """newburyWeb"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  filters,
  "org.webjars" %% "webjars-play" % "2.2.0", 
    "com.typesafe.slick" %% "slick" % "2.0.0",
  "net.sf.barcode4j" % "barcode4j" % "2.0"
)

playScalaSettings

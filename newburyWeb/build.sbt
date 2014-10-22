name := """newburyWeb"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  filters,
  //"org.webjars" %% "webjars-play" % "2.3.1", 
  //"com.typesafe.slick" %% "slick" % "2.0.2",
  //"com.typesafe.slick" %% "slick" % "2.1.0-RC2",
  //"org.webjars" %% "webjars-play" % "2.2.2",
  "com.typesafe.play" %% "play-slick" % "0.8.0-RC2",
  //"org.webjars" % "bootstrap" % "3.0.0",
  //"org.webjars" % "knockout" % "2.3.0",
  //"org.webjars" % "requirejs" % "2.1.11-1",
  "net.sf.barcode4j" % "barcode4j" % "2.0",
  "org.scala-lang.modules"  %% "scala-xml" % "1.0.1"
)



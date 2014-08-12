name := """newburyCore"""

version := "1.0.1"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  //javaCore,  // The core Java API
  //"com.typesafe.slick" %% "slick" % "2.0.2",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "mysql" % "mysql-connector-java" % "5.1.29"
  // Add your own project dependencies in the form:
  // "group" % "artifact" % "version"
)


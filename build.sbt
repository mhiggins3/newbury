name := """newbury"""

version := "1.0.1"

scalaVersion := "2.11.1"

Common.buildSettings 

lazy val newburyCore = (project in file("newburyCore"))
    .enablePlugins(PlayScala)

lazy val newburyWeb = (project in file("newburyWeb"))
    .enablePlugins(PlayScala)
    .dependsOn(newburyCore)
    .aggregate(newburyCore)

lazy val root = (project in file ("."))
    .aggregate(newburyCore, newburyWeb)


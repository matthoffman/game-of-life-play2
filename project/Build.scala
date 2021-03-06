import sbt._
import Keys._
import sbtassembly.Plugin._

object MinimalBuild extends Build {

  lazy val buildVersion = "2.0"

  lazy val typesafe = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  lazy val typesafeSnapshot = "Typesafe Snapshots Repository" at "http://repo.typesafe.com/typesafe/snapshots/"

  lazy val root = Project(id = "life", base = file("."), settings = Project.defaultSettings).settings(
    version := buildVersion,
    organization := "org.mhoffman",
    resolvers += typesafe,
    resolvers += typesafeSnapshot,
    libraryDependencies += "com.typesafe" %% "play-mini" % buildVersion,
    libraryDependencies += "com.google.guava" % "guava" % "11.0.1",
    libraryDependencies += "org.scalatest" %% "scalatest" % "1.7.2" % "test",
    libraryDependencies += "junit" % "junit" % "4.10" % "test",

    mainClass in(Compile, run) := Some("play.core.server.NettyServer")
  ).settings(assemblySettings: _*)
}
import Dependencies._

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "play-ws-betfair",
    libraryDependencies ++= Seq(
      akka,
      wsStandalone,
      slf4j,
      scalaTest
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

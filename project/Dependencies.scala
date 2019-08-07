import sbt._

object Version {
  val scalaTest = "3.0.8"
  val akka = "2.5.23"
  val wsStandalone = "2.0.7"
  val slf4j = "1.7.26"
}

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest
  lazy val akka = "com.typesafe.akka" %% "akka-actor" % Version.akka
  lazy val wsStandalone = "com.typesafe.play" %% "play-ahc-ws-standalone" % Version.wsStandalone
  lazy val slf4j = "org.slf4j" % "slf4j-simple" % Version.slf4j
}

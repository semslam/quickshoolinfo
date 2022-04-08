name := """quickinfo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.mongodb" % "mongo-java-driver" % "3.4.2",
  "org.jongo" % "jongo" % "1.3.0",
  "com.typesafe.play" % "play-mailer_2.11" % "5.0.0-M1",
  "com.google.code.gson" % "gson" % "2.6.2",
  "org.avaje.ebeanorm" % "avaje-ebeanorm" % "8.1.1",
  "de.undercouch" % "bson4jackson" % "2.7.0"

)



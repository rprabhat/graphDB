name := "gdb4s"

version := "0.0.1"

scalaVersion := "2.11.0"

scalacOptions ++= Seq("-deprecation", "-feature")

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.5.0",
    "com.typesafe.akka" %% "akka-testkit" % "2.5.1",
    "org.scalatest" %% "scalatest" % "3.0.3" % "test"
)

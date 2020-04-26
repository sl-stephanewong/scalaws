name := """scalaws"""

lazy val root = (project in file("."))

version := "0.1"

scalaVersion := "2.13.1"

scalacOptions in (Compile,run) ++= Seq("-deprecation")

val typesafeConfigVersion = "1.4.0"


libraryDependencies ++= Seq(
  "com.typesafe" % "config" % typesafeConfigVersion,
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.769"
)
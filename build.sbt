name := """scalaws"""

lazy val root = (project in file("."))

version := "0.1"

scalaVersion := "2.13.1"

scalacOptions in (Compile,run) ++= Seq("-deprecation")

val typesafeConfigVersion = "1.4.0"

val scalaTestVersion = "3.1.1"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % typesafeConfigVersion,
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.769",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "org.slf4j" % "slf4j-simple" % "1.7.30",
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.0.2",
  "dev.zio" %% "zio" % "1.0.0-RC18-2",

  // jdbc connector with quill
  "org.postgresql" % "postgresql" % "42.2.8",
  "mysql" % "mysql-connector-java" % "8.0.17",
  "com.microsoft.sqlserver" % "mssql-jdbc" % "7.4.1.jre8",
  "io.getquill" %% "quill-jdbc" % "3.5.1",

  // test
  "org.scalactic" %% "scalactic" % scalaTestVersion,
  "org.scalamock" %% "scalamock" % "4.4.0" % Test,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",

)
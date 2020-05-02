package com.scalaws.models

object RDSType extends Enumeration {
  type RDSType = Value
  val H2 = Value("H2")
  val Mysql = Value("Mysql")
  val Postgres = Value("Postgres")
  val SqlServer = Value("SqlServer")
}

trait Record extends Product with Serializable

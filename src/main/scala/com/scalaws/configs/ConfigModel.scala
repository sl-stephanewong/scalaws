package com.scalaws.configs

trait ConfigNamespace {
  val namespace: String
}

trait DatabaseConfigBuilder {
  val host: String
  val port: Option[String] = None
  val db: Option[String] = None
  val user: Option[String] = None
  val wd: Option[String] = None
}

trait ApiConfigBuilder {
  val url: String
  val user: Option[String] = None
  val token: Option[String] = None
}

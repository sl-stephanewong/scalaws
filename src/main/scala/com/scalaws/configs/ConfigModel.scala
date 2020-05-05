package com.scalaws.configs

import com.typesafe.config.Config

import scala.util.Try

trait ConfigNamespace {
  def namespace: String
}

abstract class ConfigBuilder extends ConfigNamespace {
  def getConfigField(namespace: String, field: String) = s"$namespace.$field"
}

abstract class DatabaseConfigBuilder(config: Config) extends ConfigBuilder {

  override def namespace: String = "scalaws.dbs"

  lazy val host: String = config.getString(getConfigField(namespace, "host"))
  lazy val port: Option[Int] = Try(config.getInt(getConfigField(namespace, "port"))).toOption
  lazy val db: String = config.getString(getConfigField(namespace, "db"))
  lazy val user: Option[String] = Try(config.getString(getConfigField(namespace, "username"))).toOption
  lazy val pwd: Option[String] = Try(config.getString(getConfigField(namespace, "password"))).toOption
}

abstract class ApiConfigBuilder(config: Config) extends ConfigBuilder {
  override def namespace: String = "scalaws.apis"
  lazy val url: String = config.getString(getConfigField(namespace, "host"))
  lazy val user: Option[String] = Try(config.getString(getConfigField(namespace, "username"))).toOption
  lazy val token: Option[String] = Try(config.getString(getConfigField(namespace, "token"))).toOption
}

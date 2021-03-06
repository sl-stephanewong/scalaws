package com.scalaws.configs.dbs

import com.scalaws.configs.{ConfigNamespace, DatabaseConfigBuilder}
import com.typesafe.config.Config

case class PostgreSQLConfigBuilder(config: Config) extends DatabaseConfigBuilder(config) {
  override val namespace: String = s"${super.namespace}.postgres"
}
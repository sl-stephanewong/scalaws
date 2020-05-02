package com.scalaws.configs.dbs

import com.scalaws.configs.DatabaseConfigBuilder
import com.typesafe.config.Config

case class H2ConfigBuilder(config: Config) extends DatabaseConfigBuilder(config) {
  override val namespace: String = s"${super.namespace}.h2"
}

package com.scalaws.configs.apis

import com.scalaws.configs.ApiConfigBuilder
import com.typesafe.config.Config

class CommonApiConfigBuilder(config: Config, name: String) extends ApiConfigBuilder(config) {
  override def namespace: String = s"${super.namespace}.$name"
}

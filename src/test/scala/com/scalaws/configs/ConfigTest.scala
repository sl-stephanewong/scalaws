package com.scalaws.configs

import com.typesafe.config.{Config, ConfigFactory}

trait ConfigTest {
  val config: Config = ConfigFactory.load("test/application.conf")
}

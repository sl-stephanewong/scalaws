package com.scalaws.configs

import com.typesafe.config.ConfigFactory

trait ConfigTest {
  val config = ConfigFactory.load("test/application.conf")
}

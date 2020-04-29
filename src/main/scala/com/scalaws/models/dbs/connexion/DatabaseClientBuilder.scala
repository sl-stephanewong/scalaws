package com.scalaws.models.dbs.connexion

import com.typesafe.config.Config

abstract class DatabaseClientBuilder(config: Config) {
  val url: String
}

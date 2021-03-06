package com.scalaws.models.dbs.connection

import com.scalaws.configs.dbs.MongoConfigBuilder
import com.typesafe.config.Config
import org.mongodb.scala.MongoClient

case class MongoClientBuilder(config: Config) extends DatabaseUrlBuilder {

  protected val mongoConfigBuilder = MongoConfigBuilder(config)

  override val url: String = {
    val userPwd = for {
      username <- mongoConfigBuilder.user
      password <- mongoConfigBuilder.pwd
    } yield {
      if(username.isEmpty) "" else s"$username:$password@"
    }
    s"mongodb://${userPwd.getOrElse("")}${mongoConfigBuilder.host}:${mongoConfigBuilder.port.getOrElse(27017)}"
  }

  lazy val mongoClient = MongoClient(url)
  lazy val database = mongoClient.getDatabase(mongoConfigBuilder.db)

}

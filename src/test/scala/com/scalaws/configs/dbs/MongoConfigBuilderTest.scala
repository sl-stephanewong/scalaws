package com.scalaws.configs.dbs

import com.scalaws.configs.ConfigTest
import com.typesafe.config.ConfigFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MongoConfigBuilderTest extends AnyFlatSpec with Matchers with ConfigTest {

  it should "get the mongo config" in {
    val mongocb = MongoConfigBuilder(config)
    println(mongocb)
    mongocb.namespace should be("scalaws.dbs.mongo")
    mongocb.host should be("localhost")
    mongocb.port should be(Some(27017))
    mongocb.db should be("mongo")
    mongocb.user should be(Some("test"))
    mongocb.pwd should be(Some("pass"))
  }

}

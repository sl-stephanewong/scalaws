package com.scalaws.models.dbs.connection

import com.scalaws.configs.ConfigTest
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MongoClientBuilderTest extends AnyFlatSpec with Matchers with ConfigTest {

  val mongoClientBuilder = MongoClientBuilder(config)

  it should "get the right url connexion" in {
    mongoClientBuilder.url should be("mongodb://test:pass@localhost:27017")
  }

}

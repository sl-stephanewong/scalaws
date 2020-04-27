package com.scalaws.configs.dbs

import com.typesafe.config.ConfigFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class PostgresConfigBuilderTest extends AnyFlatSpec with Matchers {

  val config = ConfigFactory.load()

  it should "get the postgres config" in {
    val pgcb = PostgresConfigBuilder(config)
    pgcb.namespace should be("scalaws.dbs.postgres")
    pgcb.host should be("postgreshost")
    pgcb.port should be(Some(5432))
    pgcb.db should be("postgres")
    pgcb.pwd should be(Some(""))
    pgcb.user should be(Some(""))
  }

}

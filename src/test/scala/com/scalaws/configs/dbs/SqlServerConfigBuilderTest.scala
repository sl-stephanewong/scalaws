package com.scalaws.configs.dbs

import com.scalaws.configs.ConfigTest
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SqlServerConfigBuilderTest extends AnyFlatSpec with Matchers with ConfigTest {

  it should "get the sqlserver config" in {
    val mongocb = SqlServerConfigBuilder(config)
    mongocb.namespace should be("scalaws.dbs.sqlserver")
    mongocb.host should be("localhost")
    mongocb.port should be(Some(1433))
    mongocb.db should be("sqlserver")
    mongocb.user should be(Some(""))
    mongocb.pwd should be(None)
  }

}

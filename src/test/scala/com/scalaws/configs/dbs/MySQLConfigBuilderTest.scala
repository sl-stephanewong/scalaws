package com.scalaws.configs.dbs

import com.scalaws.configs.ConfigTest
import com.typesafe.config.ConfigFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MySQLConfigBuilderTest extends AnyFlatSpec with Matchers with ConfigTest {

  it should "get the mysql config" in {
    val mysqlcb = MySQLConfigBuilder(config)
    mysqlcb.namespace should be("scalaws.dbs.mysql")
    mysqlcb.host should be("mysqlhost")
    mysqlcb.port should be(Some(3306))
    mysqlcb.db should be("mysql")
    mysqlcb.pwd should be(Some(""))
    mysqlcb.user should be(Some(""))

  }

}

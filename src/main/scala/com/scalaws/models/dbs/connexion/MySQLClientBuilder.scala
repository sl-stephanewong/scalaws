package com.scalaws.models.dbs.connexion

import com.mysql.cj.jdbc.MysqlDataSource
import com.scalaws.configs.dbs.{MySQLConfigBuilder, PostgreSQLConfigBuilder}
import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{MySQLDialect, MysqlJdbcContext, SnakeCase}

case class MySQLClientBuilder(config: Config)
  extends DatabaseClientBuilder[MySQLDialect, SnakeCase, MysqlJdbcContext[SnakeCase]](config) {

  override val sqlBuilder = MySQLConfigBuilder(config)
  import sqlBuilder._

  override val url: String =
    s"jdbc:mysql://address=(host=$host)(port=$port)(user=$user)(password=$pwd)/$db"

  override def getConnection = {
    val myqDataSource = new MysqlDataSource()
    val cfg = new HikariConfig()
    myqDataSource.setUrl(url)
    cfg.setDataSource(myqDataSource)
    new MysqlJdbcContext(SnakeCase, new HikariDataSource(cfg))
  }

}


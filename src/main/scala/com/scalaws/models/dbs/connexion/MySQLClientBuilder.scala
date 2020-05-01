package com.scalaws.models.dbs.connexion

import com.mysql.cj.jdbc.MysqlDataSource
import com.scalaws.configs.dbs.PostgreSQLConfigBuilder
import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{MySQLDialect, MysqlJdbcContext, SnakeCase}

case class MySQLClientBuilder(config: Config)
  extends DatabaseClientBuilder[MySQLDialect, SnakeCase, MysqlJdbcContext[SnakeCase]](config) {

  protected val myqbuilder = PostgreSQLConfigBuilder(config)

  override val url: String =
    s"jdbc:postgresql://${myqbuilder.host}:${myqbuilder.port.getOrElse(5432)}/${myqbuilder.db}"

  override def getConnection = {
    val myqDataSource = new MysqlDataSource()
    val cfg = new HikariConfig()
    myqDataSource.setUrl(url)
    cfg.setDataSource(myqDataSource)
    new MysqlJdbcContext(SnakeCase,new HikariDataSource(cfg))
  }

}


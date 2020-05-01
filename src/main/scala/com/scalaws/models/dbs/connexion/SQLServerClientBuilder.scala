package com.scalaws.models.dbs.connexion

import com.microsoft.sqlserver.jdbc.SQLServerDataSource
import com.mysql.cj.jdbc.MysqlDataSource
import com.scalaws.configs.dbs.PostgreSQLConfigBuilder
import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{MySQLDialect, MysqlJdbcContext, SQLServerDialect, SnakeCase, SqlServerJdbcContext}

case class SQLServerClientBuilder(config: Config)
  extends DatabaseClientBuilder[SQLServerDialect, SnakeCase, SqlServerJdbcContext[SnakeCase]](config) {

  protected val myqbuilder = PostgreSQLConfigBuilder(config)

  override val url: String =
    s"jdbc:postgresql://${myqbuilder.host}:${myqbuilder.port.getOrElse(5432)}/${myqbuilder.db}"

  override def getConnection = {
    val sqlDataSource = new SQLServerDataSource()
    val cfg = new HikariConfig()
    sqlDataSource.setURL(url)
    cfg.setDataSource(sqlDataSource)
    new SqlServerJdbcContext(SnakeCase,new HikariDataSource(cfg))
  }

}



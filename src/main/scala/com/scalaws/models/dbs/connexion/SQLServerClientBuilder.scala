package com.scalaws.models.dbs.connexion

import com.microsoft.sqlserver.jdbc.SQLServerDataSource
import com.scalaws.configs.dbs.SqlServerConfigBuilder
import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{SQLServerDialect, SnakeCase, SqlServerJdbcContext}

case class SQLServerClientBuilder(config: Config)
  extends DatabaseClientBuilder[SQLServerDialect, SnakeCase, SqlServerJdbcContext[SnakeCase]](config) {

  override protected val sqlBuilder = SqlServerConfigBuilder(config)
  import sqlBuilder._

  override val url: String = {
    val userPwd = for {
      u <- user
      p <- pwd
    } yield {
      if (u.isEmpty) "" else s";user=$u;password=$p"
    }
    s"jdbc:sqlserver://$host:${port.getOrElse(1433)};databaseName=$db${userPwd.getOrElse("")}"
  }

  override def getConnection: SqlServerJdbcContext[SnakeCase] = {
    val sqlDataSource = new SQLServerDataSource()
    val cfg = new HikariConfig()
    sqlDataSource.setURL(url)
    cfg.setDataSource(sqlDataSource)
    new SqlServerJdbcContext(SnakeCase,new HikariDataSource(cfg))
  }

}



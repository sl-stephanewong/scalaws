package com.scalaws.models.dbs.connection

import com.microsoft.sqlserver.jdbc.SQLServerDataSource
import com.scalaws.configs.dbs.SqlServerConfigBuilder
import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{NamingStrategy, SQLServerDialect, SnakeCase, SqlServerJdbcContext}

case class SQLServerClientBuilder[N <: NamingStrategy](config: Config, naming: N)
  extends DatabaseClientBuilder[SQLServerDialect, N, SqlServerJdbcContext[N]](config) {

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

  override def getConnection: SqlServerJdbcContext[N] = {
    val sqlDataSource = new SQLServerDataSource()
    val cfg = new HikariConfig()
    sqlDataSource.setURL(url)
    cfg.setDataSource(sqlDataSource)
    new SqlServerJdbcContext(naming,new HikariDataSource(cfg))
  }

}



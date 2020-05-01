package com.scalaws.models.dbs.connexion

import com.microsoft.sqlserver.jdbc.SQLServerDataSource
import com.mysql.cj.jdbc.MysqlDataSource
import com.scalaws.utils.Logger
import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{MysqlJdbcContext, NamingStrategy, PostgresJdbcContext, SnakeCase, SqlServerJdbcContext}
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom
import javax.sql.DataSource

trait DatabaseUrlBuilder extends Logger {
  val url: String
}

abstract class DatabaseClientBuilder[Dialect <: SqlIdiom, Naming <: NamingStrategy,
                                      T <: JdbcContext[Dialect, Naming]](config: Config)
  extends DatabaseUrlBuilder {

  def getConnection: T

}

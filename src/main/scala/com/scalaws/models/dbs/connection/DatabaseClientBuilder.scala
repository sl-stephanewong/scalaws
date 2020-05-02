package com.scalaws.models.dbs.connection

import com.scalaws.configs.DatabaseConfigBuilder
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import io.getquill.NamingStrategy
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom


trait DatabaseUrlBuilder extends LazyLogging {
  val url: String
}

abstract class DatabaseClientBuilder[Dialect <: SqlIdiom, Naming <: NamingStrategy,
                                      T <: JdbcContext[Dialect, Naming]](config: Config)
  extends DatabaseUrlBuilder {

  protected val sqlBuilder: DatabaseConfigBuilder

  def getConnection: T

}

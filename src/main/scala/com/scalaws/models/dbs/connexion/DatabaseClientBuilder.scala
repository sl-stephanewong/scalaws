package com.scalaws.models.dbs.connexion

import com.scalaws.configs.DatabaseConfigBuilder
import com.scalaws.utils.Logger
import com.typesafe.config.Config
import io.getquill.NamingStrategy
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom

trait DatabaseUrlBuilder extends Logger {
  val url: String
}

abstract class DatabaseClientBuilder[Dialect <: SqlIdiom, Naming <: NamingStrategy,
                                      T <: JdbcContext[Dialect, Naming]](config: Config)
  extends DatabaseUrlBuilder {

  protected val sqlBuilder: DatabaseConfigBuilder

  def getConnection: T

}

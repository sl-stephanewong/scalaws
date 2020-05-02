package com.scalaws.models.dbs.connection

import com.scalaws.configs.DatabaseConfigBuilder
import com.scalaws.models.RDSType
import com.scalaws.models.RDSType.RDSType
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

object DatabaseClientBuilder {
  def apply[N <: NamingStrategy](config: Config, rdsType: RDSType, namingStrategy: N) = {
    rdsType match {
      case RDSType.H2 => H2ClientBuilder[N](config, namingStrategy)
      case RDSType.Mysql => MySQLClientBuilder[N](config, namingStrategy)
      case RDSType.Postgres => PostgreSQLClientBuilder[N](config, namingStrategy)
      case RDSType.SqlServer => SQLServerClientBuilder[N](config, namingStrategy)
    }
  }
}
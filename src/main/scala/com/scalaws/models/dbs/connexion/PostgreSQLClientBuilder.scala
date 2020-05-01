package com.scalaws.models.dbs.connexion


import com.scalaws.configs.dbs.PostgreSQLConfigBuilder
import com.typesafe.config.Config
import io.getquill.{LowerCase, PostgresDialect, PostgresJdbcContext, SnakeCase}
import com.zaxxer.hikari._

case class PostgreSQLClientBuilder(config: Config)
  extends DatabaseClientBuilder[PostgresDialect, SnakeCase, PostgresJdbcContext[SnakeCase]](config) {

  protected val pgbuilder = PostgreSQLConfigBuilder(config)

  override val url: String =
    s"jdbc:postgresql://${pgbuilder.host}:${pgbuilder.port.getOrElse(5432)}/${pgbuilder.db}"

  override def getConnection = {
    val pgDataSource = new org.postgresql.ds.PGSimpleDataSource()
    val cfg = new HikariConfig()
    pgDataSource.setUrl(url)
    cfg.setDataSource(pgDataSource)
    new PostgresJdbcContext(SnakeCase,new HikariDataSource(cfg))
  }

}

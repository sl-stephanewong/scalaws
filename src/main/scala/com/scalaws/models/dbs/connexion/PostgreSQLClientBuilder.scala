package com.scalaws.models.dbs.connexion


import com.scalaws.configs.dbs.PostgreSQLConfigBuilder
import com.typesafe.config.Config
import io.getquill.{LowerCase, PostgresJdbcContext}
import com.zaxxer.hikari._

case class PostgreSQLClientBuilder(config: Config) extends DatabaseClientBuilder(config) {

  val postgreSQLConfigBuilder = PostgreSQLConfigBuilder(config)

  override val url: String =
    s"jdbc:postgresql://${postgreSQLConfigBuilder.host}:${postgreSQLConfigBuilder.port.getOrElse(5432)}/${postgreSQLConfigBuilder.db}"

  def getConnection = {
    val pgDataSource = new org.postgresql.ds.PGSimpleDataSource()
    val cfg = new HikariConfig()
    pgDataSource.setUrl(url)
    cfg.setDataSource(pgDataSource)
    new PostgresJdbcContext(LowerCase,new HikariDataSource(cfg))
  }

}

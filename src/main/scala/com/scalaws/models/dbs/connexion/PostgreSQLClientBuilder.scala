package com.scalaws.models.dbs.connexion


import com.scalaws.configs.dbs.PostgreSQLConfigBuilder
import com.typesafe.config.Config
import com.zaxxer.hikari._
import io.getquill.{NamingStrategy, PostgresDialect, PostgresJdbcContext}

case class PostgreSQLClientBuilder[N <: NamingStrategy](config: Config, naming: N)
  extends DatabaseClientBuilder[PostgresDialect, N, PostgresJdbcContext[N]](config) {

  override protected val sqlBuilder = PostgreSQLConfigBuilder(config)
  import sqlBuilder._

  override val url: String = {
    val userPwd = for {
      u <- user
      p <- pwd
    } yield { if(u.isEmpty) "" else s"?user=$u&password=$p" }
    s"jdbc:postgresql://${host}:${port.getOrElse(5432)}/${db}${userPwd.getOrElse("")}"
  }


  override def getConnection: PostgresJdbcContext[N] = {
    val pgDataSource = new org.postgresql.ds.PGSimpleDataSource()
    val cfg = new HikariConfig()
    pgDataSource.setUrl(url)
    cfg.setDataSource(pgDataSource)
    new PostgresJdbcContext(naming,new HikariDataSource(cfg))
  }

}

package com.scalaws.models.dbs.connection

import com.scalaws.configs.dbs.{H2ConfigBuilder, PostgreSQLConfigBuilder}
import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{H2Dialect, H2JdbcContext, NamingStrategy, PostgresDialect, PostgresJdbcContext}
import org.h2.jdbcx.JdbcDataSource

case class H2ClientBuilder[N <: NamingStrategy](config: Config, naming: N)
  extends DatabaseClientBuilder[H2Dialect, N, H2JdbcContext[N]](config) {

  override protected val sqlBuilder = H2ConfigBuilder(config)
  import sqlBuilder._

  override val url: String = s"jdbc:h2:mem:$db"

  override def getConnection: H2JdbcContext[N] = {
    val h2DataSource = new JdbcDataSource()
    val cfg = new HikariConfig()
    h2DataSource.setUser(user.getOrElse("sa"))
    h2DataSource.setPassword(pwd.getOrElse(""))
    h2DataSource.setUrl(url)
    cfg.setDataSource(h2DataSource)
    new H2JdbcContext(naming, new HikariDataSource(cfg))
  }

}


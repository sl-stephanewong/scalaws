package com.scalaws.models.dbs.connexion

import com.mysql.cj.jdbc.MysqlDataSource
import com.scalaws.configs.dbs.MySQLConfigBuilder
import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{MySQLDialect, MysqlJdbcContext, NamingStrategy}

case class MySQLClientBuilder[N <: NamingStrategy](config: Config, naming: N)
  extends DatabaseClientBuilder[MySQLDialect, N, MysqlJdbcContext[N]](config) {

  override protected val sqlBuilder = MySQLConfigBuilder(config)
  import sqlBuilder._

  override val url: String = {
    val userPwd = for {
      u <- user
      p <- pwd
    } yield { if(u.isEmpty) "" else s"(user=$u)(password=$p)" }
    s"jdbc:mysql://address=(host=$host)(port=${port.getOrElse(3306)})${userPwd.getOrElse("")}/$db"
  }

  override def getConnection: MysqlJdbcContext[N] = {
    val sqlDataSource = new MysqlDataSource()
    val cfg = new HikariConfig()
    sqlDataSource.setUrl(url)
    cfg.setDataSource(sqlDataSource)
    new MysqlJdbcContext(naming, new HikariDataSource(cfg))
  }

}


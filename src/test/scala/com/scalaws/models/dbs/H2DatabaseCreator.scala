package com.scalaws.models.dbs

import java.io.File
import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.LazyLogging
import org.h2.tools.RunScript

case class DatabaseConnectionSettingsTest(connectionString: String,
                                      username: String,
                                      password: String,
                                      driver: String)

object H2DatabaseCreatorTest extends LazyLogging {
  private val inputH2Url = "jdbc:h2:mem:test;USER=sa;DB_CLOSE_DELAY=-1"

  val inputDbConf = DatabaseConnectionSettingsTest(H2DatabaseCreatorTest.inputH2Url, "sa", "", "org.h2.Driver")

  def createTables(): Unit = {
    logger.info("Creating tables in test databases")
    RunScript.execute(inputH2Url, "sa", "", "src/test/resources/test/input.sql", StandardCharsets.UTF_8, false)
    logger.info("Create scripts run successfully")
  }

  def dropTables(): Unit = {
    logger.info("Dropping all tables from test databases")
    RunScript.execute(inputH2Url, "sa", "", "src/test/resources/test/output.sql", StandardCharsets.UTF_8, false)
    logger.info("Tables dropped")
  }
}

package com.scalaws.models.dbs.crud

import com.scalaws.models.RDSType.RDSType
import com.scalaws.models.Record
import com.scalaws.models.dbs.connection.DatabaseClientBuilder
import com.typesafe.config.Config
import io.getquill.NamingStrategy

import scala.concurrent.Future

abstract class RDSCRUD[T <: Record, N <: NamingStrategy](config: Config, rdsType: RDSType, namingStrategy: N) extends CRUD[T] {
  lazy val client = DatabaseClientBuilder.apply(config, rdsType, namingStrategy)
}

trait CRUD[T <: Record] {

  /**
   *
   * @return list of records
   */
  def find(): Future[List[T]]

  /**
   *
   * @param record
   * @return a 'primitive' value or nothing after insert data in database
   */
  def insert(record: T): Future[_ <: AnyVal]

  /**
   *
   * @param records
   * @return a 'primitive' value or nothing after insert data in database
   */
  def insert(records: Seq[T]): Future[_ <: Any]

  /**
   *
   * @param record
   * @return a 'primitive' value or nothing after update data in database
   */
  def update(record: T): Future[_ <: AnyVal]

  /**
   *
   * @param records
   * @return a 'primitive' value or nothing after update data in database
   */
  def update(records: Seq[T]): Future[_ <: Any]

  /**
   *
   * @param record
   * @return a 'primitive' value after deleted user
   */
  def delete(record: T): Future[_ <: AnyVal]

}

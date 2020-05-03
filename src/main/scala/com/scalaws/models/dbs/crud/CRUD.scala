package com.scalaws.models.dbs.crud

import com.scalaws.models.Record

import scala.concurrent.Future

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

package com.scalaws.tasks

import com.scalaws.models.Record
import com.scalaws.models.dbs.crud.CRUD

object TransferTask {

  def transfertAsInsert[T <: Record, U <: CRUD[T]](records: List[T], dstSource: U) = {
    dstSource.insert(records)
  }

  def transfertAsInsert[T <: Record, U <: CRUD[T]](record: T, dstSource: U) = {
    dstSource.insert(record)
  }

  def transfertAsUpdate[T <: Record, U <: CRUD[T]](records: List[T], dstSource: U) = {
    dstSource.update(records)
  }

  def transfertAsUpdate[T <: Record, U <: CRUD[T]](record: T, dstSource: U) = {
    dstSource.update(record)
  }

}

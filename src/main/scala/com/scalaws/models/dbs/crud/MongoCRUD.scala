package com.scalaws.models.dbs.crud

import com.scalaws.models.Record
import com.scalaws.models.dbs.connexion.MongoClientBuilder
import com.typesafe.config.Config
import org.mongodb.scala.MongoCollection

case class MongoCRUD(config: Config) {
  val client = MongoClientBuilder(config)

  def getCollection[T <: Record](name: String): MongoCollection[T] = client.database.getCollection(name)

  def insert[T <: Record](collectionName: String, record: T) = {
    getCollection[T](collectionName).insertOne(record)
  }

  def insert[T <: Record](collectionName: String, records: List[T]) = {
    getCollection[T](collectionName).insertMany(records)
  }

}

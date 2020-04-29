package com.scalaws.models.dbs.crud

import com.scalaws.models.Record
import com.scalaws.models.dbs.connexion.MongoClientBuilder
import com.typesafe.config.Config
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.codecs.Macros._
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}

import scala.reflect.ClassTag

case class MongoCRUD(config: Config) {

  val client = MongoClientBuilder(config)

  def getCollection[T <: Record: ClassTag](name: String): MongoCollection[T] = {
    client.database.getCollection[T](name)
  }

  def insert[T <: Record: ClassTag](collectionName: String, record: T) = {
    getCollection[T](collectionName).insertOne(record)
  }

  def insert[T <: Record: ClassTag](collectionName: String, records: List[T]) = {
    getCollection[T](collectionName).insertMany(records)
  }

}

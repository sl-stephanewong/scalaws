package com.scalaws.configs.apis

import akka.actor.{Actor, ActorLogging}
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, ActorMaterializerSettings}
import com.scalaws.models.Record
import com.typesafe.config.Config

abstract class ActorApi[T <: Record](config: Config, apiName: String) extends Actor with ActorLogging {

  final implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))
  lazy val apiConfig: CommonApiConfigBuilder = new CommonApiConfigBuilder(config, apiName)
  val http = Http(context.system)

}

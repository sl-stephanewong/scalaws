package com.scalaws.configs

import com.typesafe.config.Config

import scala.util.Try

case class S3Config(config: Config) {
  import S3Config._

  val regions = config.getString(s"$namespace.regions")
  val profile = Try(config.getString(s"$namespace.profile")).getOrElse("default")

}

object S3Config extends ConfigNamespace {
  override val namespace: String = "scalaws.s3"
}
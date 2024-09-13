package dev.mkon.scalaspark.config

import pureconfig._
import pureconfig.generic.auto._

case class DatabaseConfig(
                           name: String,
                           scheme: String,
                           host: String,
                           port: String,
                           username: Opaque,
                           password: Opaque
                         )

case class Config(db: List[DatabaseConfig])

object Config {
  private val cfg = ConfigSource.default.loadOrThrow[Config]

  def getDB(name: String): Option[DatabaseConfig] = cfg.db.find(_.name ==
    name)
}

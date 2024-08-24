package dev.mkon.scalaspark.config

import pureconfig._
import pureconfig.generic.auto._

private case class Config(db: List[Database])

object Config {
  private val cfg = ConfigSource.default.loadOrThrow[Config]

  def getDB(name: String): Option[Database] = cfg.db.find(_.name ==
    name)
}

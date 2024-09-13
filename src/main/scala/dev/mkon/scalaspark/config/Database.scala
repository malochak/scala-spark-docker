package dev.mkon.scalaspark.config

import dev.mkon.scalaspark.config.Config.getDB

trait Database {
  def name: String
  def scheme: String
  def host: String
  def port: String
  def jdbcURL: String
  def username: Opaque
  def password: Opaque
}

object Database {
  def apply(name: String): Database = new DatabaseImpl(name)

  private class DatabaseImpl(dbName: String) extends Database {
    private val db = getDB(dbName).get
    def scheme: String = db.scheme
    def host: String = db.host
    def port: String = db.port
    def name: String = db.name
    def jdbcURL: String = s"$scheme://$host:$port/$name"
    def username: Opaque = db.username
    def password: Opaque = db.password
  }
}
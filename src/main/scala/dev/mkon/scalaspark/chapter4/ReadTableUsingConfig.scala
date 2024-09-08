package dev.mkon.scalaspark.chapter4

import dev.mkon.scalaspark.config.{Config, Database}
import org.apache.spark.sql.SparkSession

case object ReadTableUsingConfig extends App {

  private[chapter4] val session = SparkSession
    .builder()
    .appName("de-with-scala")
    .master("local[*]")
    .getOrCreate()

  private val postgresDB = Config.getDB("scala_spark_db").get

  private def getDBParams(db: Database): String => Option[String] = {
    case "scheme" => Some(db.scheme)
    case "host" => Some(db.host)
    case "port" => Some(db.port)
    case "name" => Some(db.name)
    case "username" => Some(db.username.value)
    case "password" => Some(db.password.value)
    case _ => None
  }

  private val scheme = getDBParams(postgresDB)("scheme").get
  private val host = getDBParams(postgresDB)("host").get
  private val port = getDBParams(postgresDB)("port").get
  private val name = getDBParams(postgresDB)("name").get
  private val username = getDBParams(postgresDB)("username").get
  private val password = getDBParams(postgresDB)("password").get

  private val airportsDF = session.read
    .format("jdbc")
    .option("url", s"$scheme://$host:$port/$name")
    .option("user", username)
    .option("password", password)
    .option("dbtable", "(select airport, city from airports where state = 'TX') qry")
    .load()

  airportsDF.printSchema()
  airportsDF.show(numRows = 200, truncate = 200, vertical = true)
  println(airportsDF.count())
}

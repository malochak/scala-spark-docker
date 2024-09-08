package dev.mkon.scalaspark.chapter4

import dev.mkon.scalaspark.config.Config
import org.apache.spark.sql.SparkSession

case object ReadTableUsingConfig extends App {

  private[chapter4] val session = SparkSession
    .builder()
    .appName("de-with-scala")
    .master("local[*]")
    .getOrCreate()

  private val postgresDB = Config.getDB("scala_spark_db")

  private def getDBParams(param: String): Option[String] = param match {
    case "scheme" => postgresDB.map(_.scheme)
    case "host" => postgresDB.map(_.host)
    case "port" => postgresDB.map(_.port)
    case "name" => postgresDB.map(_.name)
    case "username" => postgresDB.map(_.username.value)
    case "password" => postgresDB.map(_.password.value)
    case _ => None
  }

  private val scheme = getDBParams("scheme").get
  private val host = getDBParams("host").get
  private val port = getDBParams("port").get
  private val name = getDBParams("name").get
  private val username = getDBParams("username").get
  private val password = getDBParams("password").get

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

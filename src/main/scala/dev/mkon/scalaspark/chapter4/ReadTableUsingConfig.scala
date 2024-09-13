package dev.mkon.scalaspark.chapter4

import dev.mkon.scalaspark.config.Database
import org.apache.spark.sql.SparkSession

case object ReadTableUsingConfig extends App {

  private[chapter4] val session = SparkSession
    .builder()
    .appName("de-with-scala")
    .master("local[*]")
    .getOrCreate()

  private val postgresDB: Database = Database("scala_spark_db")

  private val airportsDF = session.read
    .format("jdbc")
    .option("url", postgresDB.jdbcURL)
    .option("user", postgresDB.username.value)
    .option("password", postgresDB.password.value)
    .option("dbtable", "(select airport, city from airports where state = 'TX') qry")
    .load()

  airportsDF.printSchema()
  airportsDF.show(numRows = 200, truncate = 200, vertical = true)
  println(airportsDF.count())
}

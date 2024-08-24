package dev.mkon.scalaspark.chapter4

import org.apache.spark.sql.SparkSession

object ReadTable extends App {

  private[chapter4] val session = SparkSession
    .builder()
    .appName("de-with-scala")
    .master("local[*]")
    .getOrCreate()

  private[chapter4] val airportsDF = session.read
    .format("jdbc")
    .option("url", "jdbc:postgresql://localhost:5432/scala_spark_db")
    .option("user", "admin")
    .option("password", "password") // replace the password
    .option("dbtable", "airports")
    .load()

  airportsDF.show()
}

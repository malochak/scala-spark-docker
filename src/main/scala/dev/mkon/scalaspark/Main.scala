package dev.mkon.scalaspark

import org.apache.spark.sql.SparkSession

object Main extends App {
  val spark = SparkSession.builder
    .appName("Data Engineering Project")
    .master("spark://localhost:7077")
    .config("spark.driver.host", "host.docker.internal") // Ensures Docker containers can reach the driver
    .config("spark.driver.bindAddress", "0.0.0.0") // Binds to all available interfaces
    .getOrCreate()

  import spark.implicits._


  val data = Seq(1,2,3,4,5,6,7,8,9,10).toDF("numbers")

  data.show()

  // Process data...

  spark.stop()
}
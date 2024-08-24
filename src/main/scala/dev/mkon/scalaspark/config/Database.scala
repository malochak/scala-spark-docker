package dev.mkon.scalaspark.config

case class Database(
                     name: String,
                     scheme: String,
                     host: String,
                     port: String,
                     username: Opaque,
                     password: Opaque
                   )

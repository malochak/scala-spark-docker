package dev.mkon.scalaspark.config

final case class Opaque(
                         value: String
                       ) {
  override def toString: String = "****"
}

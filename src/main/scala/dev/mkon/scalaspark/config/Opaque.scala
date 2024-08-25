package dev.mkon.scalaspark.config

final case class Opaque(value: String) extends AnyVal {
  override def toString: String = "****"
}

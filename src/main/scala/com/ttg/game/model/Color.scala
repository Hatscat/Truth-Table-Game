package com.ttg.game.model

case class Color(r: Float, g: Float, b: Float)

object Color {
  val DARK = Color(8, 8, 8)
  val BRIGHT = Color(227, 227, 227)
  val BLACK = Color(0, 0, 0)
  val WHITE = Color(255, 255, 255)
}

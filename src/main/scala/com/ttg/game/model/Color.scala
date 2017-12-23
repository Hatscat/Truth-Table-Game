package com.ttg.game.model

case class Color(r: Float, g: Float, b: Float)

object Color {
  val DARK = Color(7, 15, 7)
  val BRIGHT = Color(200, 227, 200)
  val BLACK = Color(0, 0, 0)
  val WHITE = Color(255, 255, 255)
  val GREY = Color(127, 157, 127)
  val GOOD = Color(0, 200, 0)
  val BAD = Color(200, 0, 0)
  val GOAL = Color(200, 177, 3)
  val ERROR = Color(255, 80, 80)
}

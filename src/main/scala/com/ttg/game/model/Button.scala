package com.ttg.game.model

case class Button(name: String, x: Float, y: Float, w: Float, h: Float)

object Button {
  val PLAY = Button("PLAY", 0.3F, 0.4F, 0.4F, 0.6F)
  val PROGRAM_FIELD = Button("PROGRAM", 0.3F, 0.4F, 0.4F, 0.6F)
  val NOT = Button("NOT", 0.3F, 0.4F, 0.4F, 0.6F)
  val AND = Button("AND", 0.3F, 0.4F, 0.4F, 0.6F)
  val OR = Button("OR", 0.3F, 0.4F, 0.4F, 0.6F)
  val XOR = Button("XOR", 0.3F, 0.4F, 0.4F, 0.6F)
  val BACKSPACE = Button("BACKSPACE", 0.3F, 0.4F, 0.4F, 0.6F)
  val A = Button("A", 0.3F, 0.4F, 0.4F, 0.6F)
  val B = Button("B", 0.3F, 0.4F, 0.4F, 0.6F)
  val C = Button("C", 0.3F, 0.4F, 0.4F, 0.6F)
  val D = Button("D", 0.3F, 0.4F, 0.4F, 0.6F)
  val E = Button("E", 0.3F, 0.4F, 0.4F, 0.6F)
}

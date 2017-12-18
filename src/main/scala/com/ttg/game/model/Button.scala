package com.ttg.game.model

case class Button(name: String, minVarsThreshold: Int, x: Float, y: Float, w: Float, h: Float)

object Button {
  val PLAY = Button("PLAY", 0, 0.3F, 0.45F, 0.4F, 0.1F)
  val PROGRAM_FIELD = Button("PROGRAM", 0, 0F, 0F, 1F, 0.1F)
  val NOT = Button("NOT", 0, 0.3F, 0.4F, 0.4F, 0.6F)
  val AND = Button("AND", 0, 0.3F, 0.4F, 0.4F, 0.6F)
  val OR = Button("OR", 0, 0.3F, 0.4F, 0.4F, 0.6F)
  val XOR = Button("XOR", 0, 0.3F, 0.4F, 0.4F, 0.6F)
  val BACKSPACE = Button("BACKSPACE", 0, 0.3F, 0.4F, 0.4F, 0.6F)
  val A = Button("A", 1, 0.3F, 0.4F, 0.4F, 0.6F)
  val B = Button("B", 2, 0.3F, 0.4F, 0.4F, 0.6F)
  val C = Button("C", 3, 0.3F, 0.4F, 0.4F, 0.6F)
  val D = Button("D", 4, 0.3F, 0.4F, 0.4F, 0.6F)
  val E = Button("E", 5, 0.3F, 0.4F, 0.4F, 0.6F)
}

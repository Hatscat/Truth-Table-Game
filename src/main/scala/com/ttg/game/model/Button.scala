package com.ttg.game.model

case class Button(name: String, minVarsThreshold: Int, x: Float, y: Float, w: Float, h: Float)

object Button {
  val PLAY = Button("PLAY", 0, 0.3F, 0.55F, 0.4F, 0.1F)
  val PROGRAM_FIELD = Button("PROGRAM", 0, 0F, 0F, 1F, 0.11F)

  private val _buttonW = 0.152F
  private val _buttonH = 0.1F
  private val _buttonM = 0.03F
  private var _buttonY = PROGRAM_FIELD.y + PROGRAM_FIELD.h + _buttonM

  val A = Button("A", 1, _buttonM * 2, _buttonY, _buttonW, _buttonH)
  val B = Button("B", 2, _buttonW + _buttonM * 3, _buttonY, _buttonW, _buttonH)
  val C = Button("C", 3, _buttonW * 2 + _buttonM * 4, _buttonY, _buttonW, _buttonH)
  val D = Button("D", 4, _buttonW * 3 + _buttonM * 5, _buttonY, _buttonW, _buttonH)
  val E = Button("E", 5, _buttonW * 4 + _buttonM * 6, _buttonY, _buttonW, _buttonH)

  _buttonY += _buttonH + _buttonM

  val NOT = Button("~", 0, _buttonM * 2, _buttonY, _buttonW, _buttonH)
  val AND = Button("&", 0, _buttonW + _buttonM * 3, _buttonY, _buttonW, _buttonH)
  val OR = Button("|", 0, _buttonW * 2 + _buttonM * 4, _buttonY, _buttonW, _buttonH)
  val XOR = Button("^", 0, _buttonW * 3 + _buttonM * 5, _buttonY, _buttonW, _buttonH)
  val BACKSPACE = Button("←", 0, _buttonW * 4 + _buttonM * 6, _buttonY, _buttonW, _buttonH)
}

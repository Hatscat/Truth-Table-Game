package com.ttg.game.singleton

import com.ttg.game.model.{Button, GameState}
import processing.core.PApplet

object Player {
  private var _program: String = ""
  private var _pressedButton: Button = _
  private var _keyboardIsVisible: Boolean = false

  private var _p: PApplet = _

  def program: String = _program

  def pressedButton: Button = _pressedButton

  def keyboardIsVisible: Boolean = _keyboardIsVisible

  def setup(pApplet: PApplet): Unit = {
    _p = pApplet
    reset()
  }

  def reset(): Unit = {
    _program = ""
    _pressedButton = null
    _keyboardIsVisible = false
  }

  def checkInputs(gameState: GameState): Boolean = {
    if (_p.mousePressed)
      return true
    false
  }
}

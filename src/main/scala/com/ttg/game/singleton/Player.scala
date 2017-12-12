package com.ttg.game.singleton

import com.ttg.game.helper.Collision
import com.ttg.game.model.{Button, FunctionalException, GameState}
import processing.core.PApplet

object Player {

  private var _program: String = _
  private var _pressedButton: Button = _
  private var _keyboardIsVisible: Boolean = _
  private var _scrollHeight: Float = _
  private var _error: FunctionalException = _
  private var _p: PApplet = _
  private var _gameLevel: GameLevel.type = _

  def pressedButton: Button = _pressedButton

  def scrollHeight: Float = _scrollHeight

  def keyboardIsVisible: Boolean = _keyboardIsVisible

  def program: String = _program

  def error: FunctionalException = _error

  def setup(pApplet: PApplet, gameLevel: GameLevel.type): Unit = {
    _p = pApplet
    _gameLevel = gameLevel
    reset()
  }

  def reset(): Unit = {
    _program = ""
    _pressedButton = null
    _keyboardIsVisible = false
    _scrollHeight = 0
    _error = null
  }

  def checkInputs(gameState: GameState): Boolean = {
    if (_p.mousePressed) {
      gameState.buttonList.foreach(button => {
        if (Collision.isPointInBox(_p.mouseX, _p.mouseY, button.x * _p.width, button.y * _p.height, button.w * _p.width, button.h * _p.height)) {
          button match {
            case Button.PROGRAM_FIELD => _keyboardIsVisible = !_keyboardIsVisible
            case Button.BACKSPACE => if (!_program.isEmpty) _program = _program.substring(0, _program.length - 1)
            case Button.B => if (_gameLevel.variablesQuantity >= 2) _program += button.name
            case Button.C => if (_gameLevel.variablesQuantity >= 3) _program += button.name
            case Button.D => if (_gameLevel.variablesQuantity >= 4) _program += button.name
            case Button.E => if (_gameLevel.variablesQuantity >= 5) _program += button.name
            case _ => _program += button.name // A, NOT, AND, OR, XOR
          }
          return true
        }
      })
      //      if (scroll)
      //      return true
    }
    false
  }

  def checkVictory(programInterpreter: ProgramInterpreter.type): Boolean = {
    _error = null
    try {
      if (programInterpreter.eval(_program, _gameLevel.rowsQuantity) == _gameLevel.expectedResult)
        return true
    }
    catch {
      case e: FunctionalException =>
        _error = e
        return false
    }
    false
  }
}

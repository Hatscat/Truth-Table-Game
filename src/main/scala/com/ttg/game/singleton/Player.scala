package com.ttg.game.singleton

import com.ttg.game.helper.Collision
import com.ttg.game.model.{Button, FunctionalException, GameState}
import processing.core.PApplet

object Player {

  private val _scrollThreshold = 0.1F
  private var _program: String = _
  private var _pressedButton: Button = _
  private var _keyboardIsVisible: Boolean = _
  private var _scrollDelta: Int = _
  private var _previousTouchY: Int = _
  private var _touchClicked: Boolean = _
  private var _touchReleased: Boolean = _
  private var _error: FunctionalException = _
  private var _p: PApplet = _
  private var _gameLevel: GameLevel.type = _

  def pressedButton: Button = _pressedButton

  def scrollDelta: Int = _scrollDelta

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
    _previousTouchY = _p.mouseY
    _scrollDelta = 0
    _touchClicked = false
    _touchReleased = false
    _error = null
  }

  def checkInputs(gameState: GameState): Boolean = {
    if (checkClick()) {
      _scrollDelta = 0
      gameState.buttonList.foreach(button => {
        if (Collision.isPointInBox(_p.mouseX, _p.mouseY, button.x * _p.width, button.y * _p.height, button.w * _p.width, button.h * _p.height)) {
          button match {
            case Button.PROGRAM_FIELD => _keyboardIsVisible = !_keyboardIsVisible
            case Button.BACKSPACE if _keyboardIsVisible => if (!_program.isEmpty) _program = _program.substring(0, _program.length - 1)
            case _ if _keyboardIsVisible && _gameLevel.variablesQuantity >= button.minVarsThreshold => _program += button.name
            case _ =>
          }
          return true
        }
      })
    }
    if (gameState == GameState.GAME) {
      if (_p.mousePressed) {
        if (_touchReleased) {
          _previousTouchY = _p.mouseY
          _touchReleased = false
        }

        val scroll = _p.mouseY - _previousTouchY
        if (Math.abs(scroll) > _scrollThreshold * _p.height) {
          _scrollDelta = scroll
          return true
        }
      }
      else {
        _touchReleased = true
      }
    }
    false
  }

  private def checkClick(): Boolean = {
    val result = _touchClicked && !_p.mousePressed
    _touchClicked = _p.mousePressed
    result
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

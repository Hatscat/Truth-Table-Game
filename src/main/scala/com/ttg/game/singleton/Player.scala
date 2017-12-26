package com.ttg.game.singleton

import com.ttg.game.helper.Collision
import com.ttg.game.model.{Button, FunctionalException, GameState}
import processing.core.PApplet

object Player {

  private var _program: String = _
  private var _programOutput: Long = _
  private var _pressedButton: Button = _
  private var _keyboardIsVisible: Boolean = _
  private var _scrollDelta: Int = _
  private var _touchClicked: Boolean = _
  private var _error: FunctionalException = _
  private var _p: PApplet = _
  private var _gameLevel: GameLevel.type = _

  def pressedButton: Button = _pressedButton

  def scrollDelta: Int = _scrollDelta

  def keyboardIsVisible: Boolean = _keyboardIsVisible

  def program: String = _program

  def programOutput: Long = _programOutput

  def error: FunctionalException = _error

  def setup(pApplet: PApplet, gameLevel: GameLevel.type): Unit = {
    _p = pApplet
    _gameLevel = gameLevel
    reset()
  }

  def reset(): Unit = {
    _program = ""
    _programOutput = 0
    _pressedButton = null
    _keyboardIsVisible = false // true?
    _scrollDelta = 0
    _touchClicked = false
    _error = null
  }

  def checkInputs(gameState: GameState): Boolean = {
    if (checkClick()) {
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
    if (gameState == GameState.GAME && _p.mousePressed) {
      _scrollDelta = _p.mouseY - _p.pmouseY
      if (scrollDelta * scrollDelta > 1)
        return true
    }
    else {
      _scrollDelta = 0
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
      _programOutput = programInterpreter.eval(_program, _gameLevel.rowsQuantity)
      if (_programOutput == _gameLevel.expectedResult) {
        _keyboardIsVisible = false
        return true
      }
    }
    catch {
      case e: FunctionalException =>
        _error = e
        return false
    }
    false
  }
}

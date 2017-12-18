package com.ttg.game.singleton

import com.ttg.game.model.{Button, Color, FunctionalException, GameState}
import processing.core.{PApplet, PConstants}

object GameRenderer {
  private var _p: PApplet = _
  private var _scrollY: Int = 0
  private var _maxHeight: Int = 0

  def setup(pApplet: PApplet): Unit = {
    _p = pApplet
    _p.textAlign(PConstants.CENTER, PConstants.CENTER)
    _p.strokeWeight(1)
  }

  def drawState(gameState: GameState, gameLevel: GameLevel.type, player: Player.type): Unit = {
    if (_p == null)
      throw FunctionalException.P_APPLET_MANDATORY

    gameState match {
      case GameState.SCORE => drawScoreScreen()
      case GameState.GAME => drawGameScreen(gameLevel, player)
    }
  }

  private def drawScoreScreen(): Unit = {
    _scrollY = 0
    _p.background(Color.DARK.r, Color.DARK.g, Color.DARK.b)
    _p.stroke(Color.BRIGHT.r, Color.BRIGHT.g, Color.BRIGHT.b)
    _p.textSize(32)

    GameState.SCORE.buttonList.foreach(b => {
      _p.fill(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b)
      _p.rect(b.x * _p.width, b.y * _p.height, b.w * _p.width, b.h * _p.height, 7)
      _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
      _p.text(b.name, (b.x + b.w / 2) * _p.width, (b.y + b.h / 2) * _p.height)
    })
  }

  private def drawGameScreen(gameLevel: GameLevel.type, player: Player.type): Unit = {
    _maxHeight = 10
    _scrollY = Math.min(_maxHeight, Math.max(0, player.scrollDelta))
    // background
    _p.background(Color.BRIGHT.r, Color.BRIGHT.g, Color.BRIGHT.b)

    // buttons
    _p.stroke(Color.DARK.r, Color.DARK.g, Color.DARK.b)
    _p.textSize(24)
    GameState.GAME.buttonList.foreach(b => {
      b match {
        case Button.PROGRAM_FIELD =>
          _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
          _p.rect(b.x * _p.width, b.y * _p.height, b.w * _p.width, b.h * _p.height)
        case _ if player.keyboardIsVisible && gameLevel.variablesQuantity >= b.minVarsThreshold =>
          _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
          _p.rect(b.x * _p.width, b.y * _p.height, b.w * _p.width, b.h * _p.height, 7)
        case _ =>
      }
      b match {
        case Button.BACKSPACE if player.keyboardIsVisible =>
          _p.fill(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b)
          _p.text(b.name, (b.x + b.w / 2) * _p.width, (b.y + b.h / 2) * _p.height)
        case _ if b != Button.PROGRAM_FIELD && player.keyboardIsVisible && gameLevel.variablesQuantity >= b.minVarsThreshold =>
          _p.fill(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b)
          _p.text(b.name, (b.x + b.w / 2) * _p.width, (b.y + b.h / 2) * _p.height)
        case _ =>
      }
    })

    // program

  }

}

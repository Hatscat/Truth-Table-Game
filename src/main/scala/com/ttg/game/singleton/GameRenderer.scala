package com.ttg.game.singleton

import com.ttg.game.model.{FunctionalException, GameState}
import processing.core.PApplet

object GameRenderer {
  private var _p: PApplet = _

  def setup(pApplet: PApplet): Unit = {
    _p = pApplet
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
    _p.background(0, 255, 0)

  }

  private def drawGameScreen(gameLevel: GameLevel.type, player: Player.type): Unit = {
    _p.background(0, 0, 255)

  }

}

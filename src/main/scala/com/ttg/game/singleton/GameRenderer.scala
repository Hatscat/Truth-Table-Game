package com.ttg.game.singleton

import com.ttg.game.model.GameState
import processing.core.PApplet

object GameRenderer {
  private var p: PApplet = _

  def setup(pApplet: PApplet): Unit = {
    p = pApplet
  }

  def drawState(gameState: GameState): Unit = {
    if (p == null)
      throw new NullPointerException("no PApplet in Renderer! Call setup() first.")

    p.background(0, 255, 0)
  }

  private def drawScoreScreen(): Unit = {

  }

  private def drawGameScreen(): Unit = {

  }

}

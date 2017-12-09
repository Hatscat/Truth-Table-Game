package com.ttg.game

import com.ttg.game.model.GameState
import com.ttg.game.singleton.{GameLevel, Player, ProgramInterpreter, GameRenderer}
import processing.core.PApplet

class GameApp extends PApplet {

  private val gameLevel = GameLevel
  private val player = Player
  private val programInterpreter = ProgramInterpreter
  private val gameRenderer = GameRenderer
  private val gameState = GameState.SCORES

  override def settings(): Unit = {
    gameLevel.lvl = 0

    size(1024, 768)
    //    fullScreen()
  }

  override def setup(): Unit = gameRenderer.setup(this)

  override def draw(): Unit = {
    gameRenderer.drawState(gameState)
  }
}

object GameApp extends App {
  PApplet.main("com.ttg.game.GameApp")
}


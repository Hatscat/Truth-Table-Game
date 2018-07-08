package com.ttg.game

import com.ttg.game.model.GameState
import com.ttg.game.singleton.{GameLevel, GameRenderer, Player, ProgramInterpreter}
import processing.core.PApplet

class GameApp extends PApplet {

  private val gameLevel = GameLevel
  private val player = Player
  private val programInterpreter = ProgramInterpreter
  private val gameRenderer = GameRenderer
  private var gameState = GameState.SCORE
  private var levelComplete = false

  override def settings(): Unit = {
    gameLevel.lvl = 30//0 // TODO: load current lvl from save file

    size(450, 800)
    //    fullScreen()
  }

  override def setup(): Unit = {
    player.setup(this, gameLevel)
    gameRenderer.setup(this)
    gameRenderer.drawState(gameState, gameLevel, player)
  }

  override def draw(): Unit = {
    if (levelComplete) {
      levelComplete = false
      delay(900)
      gameLevel.lvl += 1
      gameState = GameState.SCORE
      gameRenderer.drawState(gameState, gameLevel, player)
      player.reset()
    }
    if (player.checkInputs(gameState)) {
      gameState match {
        case GameState.SCORE =>
          gameState = GameState.GAME
        case GameState.GAME =>
          if (player.checkVictory(programInterpreter))
            levelComplete = true
      }
      gameRenderer.drawState(gameState, gameLevel, player)
    }
  }
}

object GameApp extends App {
  PApplet.main("com.ttg.game.GameApp")
}


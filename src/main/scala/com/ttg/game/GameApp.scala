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

  override def settings(): Unit = {
    gameLevel.lvl = 0 // TODO: load current lvl from save file

    size(1024, 768) // TODO: get screen width and height and send it to player and gameRenderer
    //    fullScreen()
  }

  override def setup(): Unit = {
    player.setup(this)
    gameRenderer.setup(this)
    gameRenderer.drawState(gameState, gameLevel, player)
  }

  override def draw(): Unit = {
    if (player.checkInputs(gameState)) {
      gameState match {
        case GameState.SCORE =>
          gameState = GameState.GAME
          gameLevel.lvl += 1
          player.reset()
        case GameState.GAME =>
          if (!player.program.isEmpty && programInterpreter.eval(player.program, gameLevel.rowsQuantity) == gameLevel.expectedResult)
            gameState = GameState.SCORE
      }
      gameRenderer.drawState(gameState, gameLevel, player)
    }
  }
}

object GameApp extends App {
  PApplet.main("com.ttg.game.GameApp")
}


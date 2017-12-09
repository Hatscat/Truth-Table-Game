package com.ttg.game.model

case class GameState(id: Int)

object GameState {
  val SCORES = GameState(1)
  val GAME = GameState(2)
}

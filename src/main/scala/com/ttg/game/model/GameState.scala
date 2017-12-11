package com.ttg.game.model

case class GameState(id: Int, buttonList: List[Button])

object GameState {
  val SCORE = GameState(1, List(Button.PLAY))
  val GAME = GameState(2, List(Button.PROGRAM_FIELD,
    Button.NOT, Button.AND, Button.OR, Button.XOR, Button.BACKSPACE,
    Button.A, Button.B, Button.C, Button.D, Button.E))
}

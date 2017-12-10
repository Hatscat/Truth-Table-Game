package com.ttg.game.singleton

import org.scalatest.FlatSpec

class GameLevelTest extends FlatSpec {

  "The GameLevel" should "setup correct values (lvl = 0)" in {
    val gameLevel = GameLevel
    assert(gameLevel.lvl == 0)
    assert(gameLevel.variablesQuantity == 1)
    assert(gameLevel.rowsQuantity == 2)
    assert(gameLevel.expectedResult == 0)
  }

  "The GameLevel" should "setup correct values (lvl = 3)" in {
    val gameLevel = GameLevel
    gameLevel.lvl = 3
    assert(gameLevel.lvl == 3)
    assert(gameLevel.variablesQuantity == 1)
    assert(gameLevel.rowsQuantity == 2)
    assert(gameLevel.expectedResult == 3)
  }

  "The GameLevel" should "setup correct values (lvl = 4)" in {
    val gameLevel = GameLevel
    gameLevel.lvl = 4
    assert(gameLevel.lvl == 4)
    assert(gameLevel.variablesQuantity == 2)
    assert(gameLevel.rowsQuantity == 4)
    assert(gameLevel.expectedResult == 0)
  }

  "The GameLevel" should "setup correct values (lvl = 19)" in {
    val gameLevel = GameLevel
    gameLevel.lvl = 19
    assert(gameLevel.lvl == 19)
    assert(gameLevel.variablesQuantity == 2)
    assert(gameLevel.rowsQuantity == 4)
    assert(gameLevel.expectedResult == 15)
  }

  "The GameLevel" should "setup correct values (lvl = 20)" in {
    val gameLevel = GameLevel
    gameLevel.lvl = 20
    assert(gameLevel.lvl == 20)
    assert(gameLevel.variablesQuantity == 3)
    assert(gameLevel.rowsQuantity == 8)
    assert(gameLevel.expectedResult == 0)
  }

  "The GameLevel" should "setup correct values (lvl = 275)" in {
    val gameLevel = GameLevel
    gameLevel.lvl = 275
    assert(gameLevel.lvl == 275)
    assert(gameLevel.variablesQuantity == 3)
    assert(gameLevel.rowsQuantity == 8)
    assert(gameLevel.expectedResult == 255)
  }

  "The GameLevel" should "setup correct values (lvl = 276)" in {
    val gameLevel = GameLevel
    gameLevel.lvl = 276
    assert(gameLevel.lvl == 276)
    assert(gameLevel.variablesQuantity == 4)
    assert(gameLevel.rowsQuantity == 16)
    assert(gameLevel.expectedResult == 0)
  }

  "The GameLevel" should "setup correct values (lvl = 65811)" in {
    val gameLevel = GameLevel
    gameLevel.lvl = 65811
    assert(gameLevel.lvl == 65811)
    assert(gameLevel.variablesQuantity == 4)
    assert(gameLevel.rowsQuantity == 16)
    assert(gameLevel.expectedResult == 65535)
  }

  "The GameLevel" should "setup correct values (lvl = 65812)" in {
    val gameLevel = GameLevel
    gameLevel.lvl = 65812
    assert(gameLevel.lvl == 65812)
    assert(gameLevel.variablesQuantity == 5)
    assert(gameLevel.rowsQuantity == 32)
    assert(gameLevel.expectedResult == 0)
  }
}

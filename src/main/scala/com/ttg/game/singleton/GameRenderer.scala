package com.ttg.game.singleton

import com.ttg.game.model.{Button, Color, FunctionalException, GameState}
import processing.core.{PApplet, PConstants}

object GameRenderer {
  private var _p: PApplet = _
  private var _scrollY: Int = 0

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

    _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
    _p.text("Level " + (GameLevel.lvl + 1), 0.5F * _p.width, 0.3F * _p.height)
    _p.text(Player.program, 0.5F * _p.width, 0.4F * _p.height)

    GameState.SCORE.buttonList.foreach(b => {
      _p.fill(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b)
      _p.rect(b.x * _p.width, b.y * _p.height, b.w * _p.width, b.h * _p.height, 7)
      _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
      _p.text(b.name, (b.x + b.w / 2) * _p.width, (b.y + b.h / 2) * _p.height)
    })
  }

  private def drawGameScreen(gameLevel: GameLevel.type, player: Player.type): Unit = {
    val rowH = (0.08 * _p.height).toInt
    var tableMarginY = (0.02 * _p.height).toInt
    val maxHeightDelta = Math.min(0, _p.height - ((GameLevel.rowsQuantity + 3) * rowH))
    _scrollY = Math.max(maxHeightDelta, Math.min(0, _scrollY + player.scrollDelta))
    // background
    _p.background(Color.BRIGHT.r, Color.BRIGHT.g, Color.BRIGHT.b)

    tableMarginY += _scrollY

    val keyboardH = ((Button.NOT.y + Button.NOT.h + 0.03F) * _p.height).toInt
    if (player.keyboardIsVisible) {
      tableMarginY += keyboardH
    }
    else {
      tableMarginY += ((Button.PROGRAM_FIELD.y + Button.PROGRAM_FIELD.h) * _p.height).toInt
    }

    // truth table
    _p.textSize(32)
    _p.stroke(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b)
    val colW = _p.width / (GameLevel.variablesQuantity + 2 + 1)
    val marginL = colW / 2
    // headers
    if (tableMarginY > -rowH) {
      for (x <- 0 until GameLevel.variablesQuantity + 2) {
        _p.fill(Color.GREY.r, Color.GREY.g, Color.GREY.b)
        _p.rect(marginL + (colW * x), tableMarginY, colW, rowH)
        _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
        if (x < GameLevel.variablesQuantity)
          _p.text("ABCDE".charAt(x), marginL + (colW * x) + colW / 2, tableMarginY + rowH / 2)
        else if (x == GameLevel.variablesQuantity)
          _p.text("?", marginL + (colW * x) + colW / 2, tableMarginY + rowH / 2)
        else
          _p.text("#", marginL + (colW * x) + colW / 2, tableMarginY + rowH / 2)
      }
    }
    // rows
    for (y <- 0 until GameLevel.rowsQuantity) {
      if (tableMarginY > -rowH * (y + 1) && tableMarginY + (rowH * (y + 1)) < _p.height) {
        for (x <- 0 until GameLevel.variablesQuantity + 2) {
          _p.stroke(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b)
          _p.fill(Color.BRIGHT.r, Color.BRIGHT.g, Color.BRIGHT.b)
          _p.rect(marginL + (colW * x), tableMarginY + (rowH * (y + 1)), colW, rowH)

          if (x < GameLevel.variablesQuantity) {
            if ((y & (1 << x)) > 0)
              _p.fill(Color.DARK.r, Color.DARK.g, Color.DARK.b)
            else
              _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
          }
          else if (x == GameLevel.variablesQuantity) {
            if ((GameLevel.expectedResult & (1 << y)) > 0)
              _p.fill(Color.GOAL.r, Color.GOAL.g, Color.GOAL.b)
            else
              _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
          }
          else {
            if ((Player.programOutput & (1 << y)) > 0) {
              if ((Player.programOutput & (1 << y)) == (GameLevel.expectedResult & (1 << y)))
                _p.fill(Color.GOOD.r, Color.GOOD.g, Color.GOOD.b)
              else
                _p.fill(Color.BAD.r, Color.BAD.g, Color.BAD.b)
            }
            else {
              _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
            }
          }
          val cellSize = Math.min(colW, rowH) * 0.9F
          _p.noStroke()
          _p.ellipse(marginL + (colW * x) + (colW / 2), tableMarginY + (rowH * (y + 1)) + (rowH / 2), cellSize, cellSize)
        }
      }
    }

    // keyboard bg
    if (player.keyboardIsVisible) {
      _p.fill(Color.GREY.r, Color.GREY.g, Color.GREY.b)
      _p.noStroke()
      _p.rect(0, 0, _p.width, keyboardH)
    }

    // buttons
    _p.stroke(Color.DARK.r, Color.DARK.g, Color.DARK.b)
    _p.textSize(32)
    GameState.GAME.buttonList.foreach(b => {
      b match {
        case Button.PROGRAM_FIELD =>
          if (Player.error != null && Player.error.equals(FunctionalException.EMPTY_STACK))
            _p.fill(Color.ERROR.r, Color.ERROR.g, Color.ERROR.b)
          else
            _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
          _p.rect(b.x * _p.width, b.y * _p.height, b.w * _p.width, b.h * _p.height)
        case _ if player.keyboardIsVisible && gameLevel.variablesQuantity >= b.minVarsThreshold =>
          _p.fill(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b)
          _p.rect(b.x * _p.width, b.y * _p.height, b.w * _p.width, b.h * _p.height, 7)
        case _ =>
      }
      b match {
        case Button.BACKSPACE if player.keyboardIsVisible =>
          _p.fill(Color.DARK.r, Color.DARK.g, Color.DARK.b)
          _p.text(b.name, (b.x + b.w / 2) * _p.width, (b.y + b.h / 2) * _p.height)
        case _ if b != Button.PROGRAM_FIELD && player.keyboardIsVisible && gameLevel.variablesQuantity >= b.minVarsThreshold =>
          _p.fill(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b)
          _p.text(b.name, (b.x + b.w / 2) * _p.width, (b.y + b.h / 2) * _p.height)
        case _ =>
      }
    })

    // program
    _p.textSize(32)
    _p.fill(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b)
    _p.text(player.program, (Button.PROGRAM_FIELD.x + Button.PROGRAM_FIELD.w / 2) * _p.width, (Button.PROGRAM_FIELD.y + Button.PROGRAM_FIELD.h / 2) * _p.height)
  }
}

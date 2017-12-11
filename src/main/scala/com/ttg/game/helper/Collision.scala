package com.ttg.game.helper

object Collision {

  def isPointInBox(pX: Float, pY: Float, bX: Float, bY: Float, bW: Float, bH: Float): Boolean = {
    pX >= bX && pX <= bX + bW && pY >= bY && pY <= bY + bH
  }
}

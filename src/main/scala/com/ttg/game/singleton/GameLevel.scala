package com.ttg.game.singleton

object GameLevel {

  private var _lvl: Long = 0
  private var _variablesQuantity: Int = 0
  private var _rowsQuantity: Int = 0
  private var _expectedResult: Long = 0

  lvl = 0

  def variablesQuantity: Int = _variablesQuantity

  def rowsQuantity: Int = _rowsQuantity

  def expectedResult: Long = _expectedResult

  def lvl: Long = _lvl

  def lvl_=(n: Long): Unit = {
    _lvl = n
    _variablesQuantity = 1 + log2(log2(_lvl - oneRotOneRotSum(log2(log2(_lvl)))))
    _rowsQuantity = 1 << _variablesQuantity
    _expectedResult = _lvl //shuffleIndex(_lvl - oneRotOneRotSum(_variablesQuantity), 1L << _rowsQuantity)
  }

  private def log2(n: Long): Int = n.toBinaryString.length - 1

  private def oneRotOneRotSum(i: Int): Int = ((i - 1) to 1 by -1).fold(0) { (a, b) => a + (1 << (1 << b)) }

  private def shuffleIndex(i: Long, len: Long): Long = {
    // reverse i
    var c = len - i - 1
    // shift i
    c = (c + 6771) % len
    // 2d map, y way
    val w = 1 << 2
    val h = len / w
    val y = c / w | 0
    val x = c % w
    x * h + y
  }
}

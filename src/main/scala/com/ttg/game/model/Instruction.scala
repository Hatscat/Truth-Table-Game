package com.ttg.game.model

case class Instruction(name: Char, popsQuantity: Int, operator: (Boolean, Boolean) => Boolean)

object Instruction {
  val NOT = Instruction('~', 1, (a: Boolean, _) => !a)
  val AND = Instruction('&', 2, (a: Boolean, b: Boolean) => a & b)
  val OR  = Instruction('|', 2, (a: Boolean, b: Boolean) => a | b)
  val XOR = Instruction('^', 2, (a: Boolean, b: Boolean) => a ^ b)
}

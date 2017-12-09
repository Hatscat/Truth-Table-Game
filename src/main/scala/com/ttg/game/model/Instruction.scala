package com.ttg.game.model

case class Instruction(name: String, operator: (Boolean, Boolean) => Boolean)

object Instruction {
  val NOT = Instruction("~", (a: Boolean, _) => !a)
  val AND = Instruction("&", (a: Boolean, b: Boolean) => a & b)
  val OR = Instruction("|", (a: Boolean, b: Boolean) => a | b)
  val XOR = Instruction("^", (a: Boolean, b: Boolean) => a ^ b)
}

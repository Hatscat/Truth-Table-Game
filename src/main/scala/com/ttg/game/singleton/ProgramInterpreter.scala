package com.ttg.game.singleton

import com.ttg.game.model.{FunctionalException, Instruction}

import scala.collection.mutable

object ProgramInterpreter {

  def eval(program: String, rowsQuantity: Int): Long = {
    val rowResults = for (i <- 0 until rowsQuantity if evalRow(program, i)) yield 1 << i
    rowResults.sum
  }

  private def evalRow(program: String, row: Int): Boolean = {

    if (program.isEmpty)
      throw FunctionalException.EMPTY_PROGRAM

    val stack = new mutable.Stack[Boolean]

    program foreach {
      case Instruction.NOT.name => execInstOnStack(Instruction.NOT, stack)
      case Instruction.AND.name => execInstOnStack(Instruction.AND, stack)
      case Instruction.OR.name  => execInstOnStack(Instruction.OR, stack)
      case Instruction.XOR.name => execInstOnStack(Instruction.XOR, stack)
      case variable => stack.push(getVariableValue(variable, row))
    }

    stack.pop()
  }

  private def execInstOnStack(instruction: Instruction, stack: mutable.Stack[Boolean]): Unit = {

    if (stack.lengthCompare(instruction.popsQuantity) < 0)
      throw FunctionalException.EMPTY_STACK

    if (instruction.popsQuantity == 1)
      stack.push(instruction.operator(stack.pop(), false))
    else
      stack.push(instruction.operator(stack.pop(), stack.pop()))
  }

  private def getVariableValue(variable: Char, row: Int): Boolean = ((1 << (variable - 65)) & row) != 0
}

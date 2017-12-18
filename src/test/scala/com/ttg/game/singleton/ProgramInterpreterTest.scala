package com.ttg.game.singleton

import com.ttg.game.model.FunctionalException
import org.scalatest.FlatSpec

class ProgramInterpreterTest extends FlatSpec {

  "The ProgramInterpreter" should "throw FunctionalException.EMPTY_PROGRAM if the program is empty" in {
    val programInterpreter = ProgramInterpreter
    assertThrows[FunctionalException] {
      programInterpreter.eval("", 2)
    }
  }

  "The ProgramInterpreter" should "throw FunctionalException.EMPTY_STACK if and empty stack is popped" in {
    val programInterpreter = ProgramInterpreter
    assertThrows[FunctionalException] {
      programInterpreter.eval("A&", 2)
    }
  }

  "The ProgramInterpreter" should "gives the correct answer for the lvl" in {
    val gameLevel = GameLevel
    val programInterpreter = ProgramInterpreter
    assert(programInterpreter.eval("AA~&", gameLevel.rowsQuantity) == gameLevel.expectedResult)
  }

  "The ProgramInterpreter" should "gives the correct answer for the lvl 13" in {
    val gameLevel = GameLevel
    val programInterpreter = ProgramInterpreter
    gameLevel.lvl = 13 // expected result == 14 -> 1110
    assert(programInterpreter.eval("AB|", gameLevel.rowsQuantity) == gameLevel.expectedResult)
  }

  "The ProgramInterpreter" should "eval a program and return the expected result" in {
    val programInterpreter = ProgramInterpreter
    assert(programInterpreter.eval("AABC&&^", 8) == 42)
  }
}

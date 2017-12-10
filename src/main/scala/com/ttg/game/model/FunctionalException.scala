package com.ttg.game.model

case class FunctionalException(message: String, cause: Throwable = None.orNull) extends Exception(message, cause)

object FunctionalException {
  val EMPTY_PROGRAM = FunctionalException("Empty program, nothing to eval.")
  val EMPTY_STACK = FunctionalException("Empty Stack, cannot pop value.")
  val P_APPLET_MANDATORY = FunctionalException("no PApplet in Renderer! Call setup() first.")
}

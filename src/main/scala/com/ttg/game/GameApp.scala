package com.ttg.game

import processing.core.PApplet

class GameApp extends PApplet {

  override def settings(): Unit = {
    size(1024, 768)
  }

  override def setup() {
    background(255)

  }

  override def draw() {
    // Your drawing code
  }
}

object GameApp extends App {
  PApplet.main("com.ttg.game.GameApp")
}


package com.ttg.game.helper

import org.scalatest.FlatSpec

class CollisionTest extends FlatSpec {

  "The Collision.isPointInBox" should "return true if there is a collision" in {
    assert(Collision.isPointInBox(0.2F, 0.3F, 0.1F, 0.15F, 0.4F, 0.2F))
  }

  "The Collision.isPointInBox" should "return false if the point is too much left" in {
    assert(!Collision.isPointInBox(0.02F, 0.3F, 0.1F, 0.15F, 0.4F, 0.2F))
  }

  "The Collision.isPointInBox" should "return false if the point is too much right" in {
    assert(!Collision.isPointInBox(0.6F, 0.3F, 0.1F, 0.15F, 0.4F, 0.2F))
  }

  "The Collision.isPointInBox" should "return false if the point is too much top" in {
    assert(!Collision.isPointInBox(0.2F, 0.1F, 0.1F, 0.15F, 0.4F, 0.2F))
  }

  "The Collision.isPointInBox" should "return false if the point is too much down" in {
    assert(!Collision.isPointInBox(0.2F, 0.4F, 0.1F, 0.15F, 0.4F, 0.2F))
  }
}

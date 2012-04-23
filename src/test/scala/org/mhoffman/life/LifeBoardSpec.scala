package org.mhoffman.life

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

/**
 *
 *
 */

class LifeBoardSpec extends FlatSpec with ShouldMatchers {
  //////////////////////////
  // living cell tests:
  //////////////////////////
  "a living cell" should "die if it has only 1 live neighbor" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(1, 0, 0, 0, 0, 0, 0, 0))
    result should equal(0)
  }

  "a living cell" should "die if it has no live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(0, 0, 0, 0, 0, 0, 0, 0))
    result should equal(0)
  }

  "a living cell" should "survive if it has 2 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(1, 1, 0, 0, 0, 0, 0, 0))
    result should equal(1)
  }

  "a living cell" should "survive if it has 2 live neighbors -- no matter which neighbors they are" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(0, 0, 1, 0, 0, 0, 0, 1))
    result should equal(1)
  }

  "a living cell" should "survive if it has 3 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(0, 1, 0, 1, 0, 0, 1, 0))
    result should equal(1)
  }

  "a living cell" should "die if it has 4 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(1, 0, 1, 0, 1, 0, 0, 1))
    result should equal(0)
  }

  "a living cell" should "die if it has 5 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(1, 0, 1, 1, 1, 0, 0, 1))
    result should equal(0)
  }

  "a living cell" should "die if it has 6 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(1, 0, 1, 1, 1, 0, 1, 1))
    result should equal(0)
  }

  //////////////////////////
  // dead cell tests:
  //////////////////////////
  "a dead cell" should "stay dead if it has only 1 live neighbor" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(0, Array(1, 0, 0, 0, 0, 0, 0, 0))
    result should equal(0)
  }

  "a dead cell" should "stay dead if it has no live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(0, Array(0, 0, 0, 0, 0, 0, 0, 0))
    result should equal(0)
  }

  "a dead cell" should "stay dead if it has 2 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(0, Array(1, 1, 0, 0, 0, 0, 0, 0))
    result should equal(0)
  }

  "a dead cell" should "stay dead if it has 2 live neighbors -- no matter which neighbors they are" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(0, Array(0, 0, 1, 0, 0, 0, 0, 1))
    result should equal(0)
  }

  "a dead cell" should "come to life if it has 3 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(0, 1, 0, 1, 0, 0, 1, 0))
    result should equal(1)
  }

  "a dead cell" should "come to life if it has 3 live neighbors -- no matter which neighbors they are" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(1, 1, 0, 0, 0, 0, 1, 0))
    result should equal(1)
  }

  "a dead cell" should "stay dead if it has 4 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(0, Array(1, 0, 1, 0, 1, 0, 0, 1))
    result should equal(0)
  }

  "a dead cell" should "stay dead if it has 5 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(0, Array(1, 0, 1, 1, 1, 0, 0, 1))
    result should equal(0)
  }

  "a dead cell" should "stay dead if it has 6 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(0, Array(1, 0, 1, 1, 1, 0, 1, 1))
    result should equal(0)
  }

  // odd data tests
  "a living cell" should "stay alive with ONLY 3 live neighbors" in {
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array(1, 1, 1))
    result should equal(1)
  }
  "a living cell" should "die with no neighbors at all" in {
    // i don't know how this could possibly happen, but might as well handle it .
    val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
    val result = lifeBoard.evolveCell(1, Array())
    result should equal(0)
  }


  "neighbor extraction" should "work at the top left corner" in {
      // i don't know how this could possibly happen, but might as well handle it .
      val lifeBoard = new LifeBoard(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0))) // note that, for this method, we don't care about the passed-in array
      val result = lifeBoard.evolveCell(1, Array())
      result should equal(0)
    }
}

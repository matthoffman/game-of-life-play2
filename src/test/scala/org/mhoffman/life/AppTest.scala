package org.mhoffman.life

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

/**
 *
 *
 */

class AppSpec extends FlatSpec with ShouldMatchers {

  "ExtractBoard" should "extract a single-character string into a 1-cell array" in {
    val board = App.extractBoard("0", 1)
    val expected = Array(Array(0))
    board.asArray() should equal(expected)
  }

  "ExtractBoard" should "extract a single-character string into a 1-cell array, take 2" in {
    val board = App.extractBoard("1", 1)
    val expected = Array(Array(1))
    board.asArray() should equal(expected)
  }

  "ExtractBoard" should "convert a blank string into an empty array" in {
    val board = App.extractBoard("", 0)
    board.asArray() should equal(Array.ofDim[Int](0, 0))
  }

  "ExtractBoard" should "throw an exception when the # of rows is invalid" in {
    intercept[IllegalArgumentException] {
      val board = App.extractBoard("010103", 4)
    }
  }

  "ExtractBoard" should "throw an exception when the # of rows is zero" in {
    intercept[IllegalArgumentException] {
      val board = App.extractBoard("0101", 0)
    }
  }

  "ExtractBoard" should "handle a 2-row string" in {
    val board = App.extractBoard("1001", 2)
    val expected = Array(Array(1, 0), Array(0, 1))
    board.asArray() should equal(expected)
  }

  "ExtractBoard" should "handle a 3-row string" in {
    val board = App.extractBoard("110100", 3)
    val expected = Array(Array(1, 1), Array(0, 1), Array(0, 0))
    board.asArray() should equal(expected)
  }



  //
  //    "A Stack" should "pop values in last-in-first-out order" in {
  //      val stack = new Stack[Int]
  //      stack.push(1)
  //      stack.push(2)
  //      stack.pop() should equal (2)
  //      stack.pop() should equal (1)
  //    }
  //
  //    it should "throw NoSuchElementException if an empty stack is popped" in {
  //      val emptyStack = new Stack[String]
  //      evaluating { emptyStack.pop() } should produce [NoSuchElementException]
  //    }
  //  }
}
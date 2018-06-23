package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._


@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }
  
  trait InfiniteLevel extends Solver with InfiniteTerrain {
    val startPos = Pos(1,3)
    val goal = Pos(5,8)
    
    val optsolution = List(Right, Down, Right, Down, Right, Right, Down)
  }

  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(!terrain(Pos(4,11)), "4,11")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }
  
    test("is standing") {
    new Level1 {
      assert(Block(Pos(1,1),Pos(1,1)).isStanding==true)
    }
  }
  
  test("legal neighbors ") {
    new Level1{
     
      assert(Block(Pos(1,1),Pos(1,1)).legalNeighbors==List((Block(Pos(1,2),Pos(1,3)),Right), (Block(Pos(2,1),Pos(3,1)),Down)))
    }
   }
  
  test("neighbours with history : 1 ") {
    new Level1{
     // println(neighborsWithHistory(Block(Pos(1,1),Pos(1,1)),List(Left,Up)))
      assert((neighborsWithHistory(Block(Pos(1,1),Pos(1,1)), List(Left,Up)) toSet) ==Set(
    		  (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
    		  (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
    		  ))
    }
  }
  
  test("new neighbours only ") {
    new Level1{
      assert(newNeighborsOnly(
  Set(
    (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
    (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
  ).toStream,

  Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1))))==Set((Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))).toStream)
    } 
  }

//  test("from test level") {
  //  new Level1 {
    //  println(from(Set((Block(Pos(1,1),Pos(1,1)),List())).toStream,Set(Block(Pos(1,1),Pos(1,1)))).toList)
    //}
  //}
  
    test("optimal solution test 0") {
    new Level1 {
      assert(solve(List(Right))==Block(Pos(1,2),Pos(1,3)))
    }
  }
  
  test("optimal solution for level 1") {
    new Level1 {
      
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }

  test("Infinite solution") {

     new InfiniteLevel {
     assert(solution.length==7)
      assert(solution==optsolution)
   
     }
  }
  
}

package astar.maze

import utest._

/**
 * Author: Phillip Johnson
 * Date: 3/14/15
 */
object MazeTest extends TestSuite {

  val simpleMazeStr = ""+
  "+-+-+\n" +
  "I   |\n" +
  "+-+ |\n" +
  "O   |\n" +
  "+-+-+"

  val simpleMaze = new Maze(simpleMazeStr)

  val tests = TestSuite {
    'ParseWalls {
      val walls = Set((0,0),(1,0),(2,0),(3,0),(4,0)
                                             ,(4,1)
                     ,(0,2),(1,2),(2,2)      ,(4,2)
                                             ,(4,3)
                     ,(0,4),(1,4),(2,4),(3,4),(4,4))

      val wallsAsCells = walls.map(point => new Cell(point._1, point._2))

      assert(simpleMaze.walls == wallsAsCells)
    }

    'ParsePaths {
      val paths = Set((0,1),(1,1),(2,1),(3,1)
                                       ,(3,2)
                     ,(0,3),(1,3),(2,3),(3,3))

      val pathsAsCells = paths.map(point => new Cell(point._1, point._2))

      assert(simpleMaze.paths == pathsAsCells)
    }

    'ParseEntrance {
      assert(simpleMaze.entrance == new Cell(0,1))
    }

    'ParseExit {
      assert(simpleMaze.exit == new Cell(0,3))
    }

  }

}

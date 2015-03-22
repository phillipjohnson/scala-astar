package astar.search

import astar.maze.{Mazes, Maze}
import utest._
import utest.framework.TestSuite

/**
 * Author: Phillip Johnson
 * Date: 3/15/15
 */
object AgentTest extends TestSuite {
  val simpleMazeStr = ""+
    "+-+-+\n" +
    "I   |\n" +
    "+-+ |\n" +
    "O   |\n" +
    "+-+-+"

  val simpleMaze = new Maze(simpleMazeStr)

  val tests = TestSuite {
    'FindsSolution {
      val q = new Agent(Mazes.maze20, Heuristics.manhattan).search()
      assert(q.isDefined)
    }

    'ManhattanBeatsNaive {
      val manhattan = new Agent(Mazes.maze20, Heuristics.manhattan)
      val naive = new Agent(Mazes.maze20, Heuristics.naive)
      manhattan.search()
      naive.search()
      assert(manhattan.searchHistory.size < naive.searchHistory.size)
    }

  }

}

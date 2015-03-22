package astar.search

import astar.maze.Maze

/**
 * Defines various heuristic functions for the A* Agent
 *
 * Author: Phillip Johnson
 * Date: 3/15/15
 */
object Heuristics {

  def manhattan(state: State, maze: Maze) = math.abs(state.x - maze.exit.x) + math.abs(state.y - maze.exit.y)

  def euclidian(state:State, maze:Maze) = {
    val exact = math.sqrt(math.pow(state.x - maze.exit.x,2) + math.pow(state.y - maze.exit.y,2))
    exact.toInt
  }

  def naive(state:State, maze:Maze) = 0
}

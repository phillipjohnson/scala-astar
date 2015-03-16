package astar.search

import astar.maze.Maze

/**
 * Author: Phillip Johnson
 * Date: 3/15/15
 */
object Heuristics {

  def manhattan(state: State, maze: Maze) = math.abs(state.x - maze.exit.x) + math.abs(state.y - maze.exit.y)
}

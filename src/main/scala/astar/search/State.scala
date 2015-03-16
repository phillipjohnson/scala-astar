package astar.search

import astar.maze.Cell

/**
 * Author: Phillip Johnson
 * Date: 3/15/15
 */
class State(cell:Cell) {
  val x = cell.x
  val y = cell.y


  def canEqual(other: Any): Boolean = other.isInstanceOf[State]

  override def equals(other: Any): Boolean = other match {
    case that: State =>
      (that canEqual this) &&
        x == that.x &&
        y == that.y
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(x, y)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

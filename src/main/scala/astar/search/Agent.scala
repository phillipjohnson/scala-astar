package astar.search

import astar.maze.{Cell, Maze}

/**
 * Author: Phillip Johnson
 * Date: 3/15/15
 */
class Agent(maze:Maze, heuristic: (State, Maze) => Int) {
  class Direction(val dx:Int, val dy:Int)
  val NORTH = new Direction(0, -1)
  val SOUTH = new Direction(0, 1)
  val EAST = new Direction(1, 0)
  val WEST = new Direction(-1, 0)


  class Successor(val move:Cell, val direction:Direction, val cost:Int)

  def successors(state:State):Set[Successor] = {
    Set(NORTH,SOUTH,EAST,WEST)
      .withFilter(d => maze.paths.contains(new Cell(state.x + d.dx, state.y + d.dy)))
      .map(d => new Successor(new Cell(state.x + d.dx, state.y + d.dy), d, 1))
  }

  def isGoal(state:State) = new Cell(state.x, state.y) == maze.exit


  class FringeElement(val state:State, val path:List[Cell], val cost:Int) extends Ordered[FringeElement] {
    override def compare(that: FringeElement): Int = cost + heuristic.apply(state, maze)
  }

  def search():Option[List[Cell]] = {
    var closed:Set[State] = Set.empty
    val fringe = new scala.collection.mutable.PriorityQueue[FringeElement]()
    fringe += new FringeElement(new State(maze.entrance), List.empty, 0)
    while(fringe.nonEmpty) {
      val next = fringe.dequeue()
      if(isGoal(next.state)) return Some(next.path)
      if(!closed.contains(next.state)) {
        closed = closed + next.state
        for(s <- successors(next.state)) {
          val newPath = next.path.::(s.move)
          val newCost = next.cost + s.cost
          fringe.enqueue(new FringeElement(new State(s.move), newPath, newCost))
        }
      }
    }
    None
  }

}

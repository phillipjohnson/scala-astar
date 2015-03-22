package astar.search

import astar.maze.{Cell, Maze}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * An A* Search agent.
 *
 * The maze is the problem to solve and the provided heuristic is used to calculate
 * the h(x) cost of a given node.
 *
 * Author: Phillip Johnson
 * Date: 3/15/15
 */
class Agent(maze:Maze, heuristic: (State, Maze) => Int) {
  class Direction(val dx:Int, val dy:Int)
  val NORTH = new Direction(0, -1)
  val SOUTH = new Direction(0, 1)
  val EAST = new Direction(1, 0)
  val WEST = new Direction(-1, 0)

  var searchHistory = new ListBuffer[List[Cell]]

  class Successor(val move:Cell, val direction:Direction, val cost:Int)

  def successors(state:State):Set[Successor] = {
    Set(NORTH,SOUTH,EAST,WEST)
      .withFilter(d => maze.paths.contains(new Cell(state.x + d.dx, state.y + d.dy)))
      .map(d => new Successor(new Cell(state.x + d.dx, state.y + d.dy), d, 1))
  }

  def isGoal(state:State) = new Cell(state.x, state.y) == maze.exit


  class FringeElement(val state:State, val path:List[Cell], val cost:Int) extends Ordered[FringeElement] {
    override def compare(that: FringeElement): Int = {
      val thisCost = this.cost + heuristic.apply(this.state, maze)
      val thatCost = that.cost + heuristic.apply(that.state, maze)
      thatCost.compare(thisCost)
    }
    override def toString = "(" + state.x + ", " + state.y + ")" + " Cells: " + path.size + " Cost: " + cost
  }

  def search():Option[List[Cell]] = {
    var closed:Set[State] = Set.empty
    val fringe = new mutable.PriorityQueue[FringeElement]()
    fringe += new FringeElement(new State(maze.entrance), List.empty, 0)
    while(fringe.nonEmpty) {
      val next = fringe.dequeue()
      if(isGoal(next.state)) {
        //Bookend with the entrance and exit for clarity
        return Some(next.path.::(maze.exit).+:(maze.entrance))
      }
      if(!closed.contains(next.state)) {
        closed = closed + next.state
        for(s <- successors(next.state)) {
          val newPath = next.path.::(s.move)
          val newCost = next.cost + s.cost
          val expansion = new FringeElement(new State(s.move), newPath, newCost)
          searchHistory += expansion.path
          fringe.enqueue(expansion)
        }
      }
    }
    None
  }

}

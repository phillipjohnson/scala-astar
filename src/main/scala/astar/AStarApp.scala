package astar

import astar.maze.Mazes
import astar.maze.Cell
import astar.search.{Heuristics, Agent}
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

import scala.scalajs.js.annotation.JSExport

@JSExport
object AStarApp extends JSApp {

  private val zoom = 10


  def main(): Unit = {
    val canvas = dom.document
      .getElementById("canvas")
      .asInstanceOf[html.Canvas]



    val ctx = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    val maze = Mazes.maze20

    val agent = new Agent(maze, Heuristics.manhattan)

    val solution:Option[List[Cell]] = agent.search()

    canvas.height = maze.height * zoom
    canvas.width = maze.width * zoom

    ctx.fillStyle = "#333333"
    for(wall <- maze.walls) {
      ctx.fillRect(wall.x * zoom, wall.y * zoom, zoom, zoom)
    }

    if(solution.isDefined) {
      dom.console.log("Nodes expanded:" + agent.searchHistory.size)
      draw(agent.searchHistory)
    }

    def draw(fringe:Seq[List[Cell]]):Unit = {
      if(fringe.nonEmpty) {
        ctx.fillStyle = "#4682b4"
        for (cell <- fringe.head) {
          ctx.beginPath()
          ctx.arc(cell.x * zoom + 5, cell.y * zoom + 5, (zoom -2)/ 2, 0, 2 * math.Pi)
          ctx.fill()
        }
        dom.setTimeout(() => draw(fringe.tail), 10)
      } else {
        val path = solution.get
        ctx.fillStyle = "#ffb958"
        for(cell <- path) {
          ctx.beginPath()
          ctx.arc(cell.x * zoom + 5, cell.y * zoom + 5, (zoom -2)/ 2, 0, 2 * math.Pi)
          ctx.fill()
        }
      }
    }
  }



}
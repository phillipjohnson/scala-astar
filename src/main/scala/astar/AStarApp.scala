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

    val solution:Option[List[Cell]] = new Agent(maze, Heuristics.manhattan).search()

    canvas.height = maze.height * zoom
    canvas.width = maze.width * zoom

    ctx.fillStyle = "red"
    for(wall <- maze.walls) {
      ctx.fillRect(wall.x * zoom, wall.y * zoom, zoom, zoom)
    }

    if(solution.isDefined) {
      val path = solution.get
      ctx.fillStyle = "blue"
      for(cell <- path) {
        ctx.fillRect(cell.x * zoom + 4, cell.y * zoom + 4, zoom - 8, zoom - 8)
      }
    }


  }

}
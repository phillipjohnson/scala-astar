package astar

import astar.maze.Mazes
import astar.maze.Cell
import astar.search.{Heuristics, Agent}
import org.scalajs.dom.html

import scala.scalajs.js.JSApp
import org.scalajs.dom

import scala.scalajs.js.annotation.JSExport

@JSExport
object AStarApp extends JSApp {

  private val GREY = "#333333"
  private val BLUE = "#4682b4"
  private val YELLOW = "#ffb958"
  private val zoom = 10 //px
  private val offset = 5 //px
  private val drawRate = 10 //milliseconds

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

    ctx.clearRect (0, 0, canvas.width, canvas.height)

    ctx.fillStyle = GREY
    for(wall <- maze.walls) {
      ctx.fillRect(wall.x * zoom, wall.y * zoom, zoom, zoom)
    }

    if(solution.isDefined) {
      dom.console.log("Nodes expanded:" + agent.searchHistory.size)
      draw(agent.searchHistory)
    }

    def draw(fringe:Seq[List[Cell]]):Unit = {
      if(fringe.nonEmpty) {
        ctx.fillStyle = BLUE
        for (cell <- fringe.head) {
          ctx.beginPath()
          ctx.arc(cell.x * zoom + offset, cell.y * zoom + offset, (zoom -2)/ 2, 0, 2 * math.Pi)
          ctx.fill()
        }
        dom.setTimeout(() => draw(fringe.tail), drawRate)
      } else {
        val path = solution.get
        ctx.fillStyle = YELLOW
        for(cell <- path) {
          ctx.beginPath()
          ctx.arc(cell.x * zoom + offset, cell.y * zoom + offset, (zoom -2)/ 2, 0, 2 * math.Pi)
          ctx.fill()
        }
      }
    }
  }
}
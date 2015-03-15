package astar

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

    canvas.height = maze.height * zoom
    canvas.width = maze.width * zoom

    ctx.fillStyle = "red"
    for(wall <- maze.walls) {
      ctx.fillRect(wall._1 * zoom, wall._2 * zoom, zoom, zoom)
    }

  }

}
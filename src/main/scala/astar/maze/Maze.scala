package astar.maze

/**
 * A problem for the A* agent to traverse.
 *
 * Mazes are generated from text strings using:
 *  +- for horizontal walls
 *  | for vertical walls
 *  spaces for paths
 *  I for the entrance
 *  O for the exit
 *
 * Author: Phillip Johnson
 * Date: 3/14/15 (mmmm...π)
 */
class Maze(val pattern:String) {

  require(pattern.contains("I"))
  require(pattern.contains("O"))

  private val WALL_STRINGS = Set('+','-','|')
  private val PATH_STRINGS = Set(' ','I','O')
  private val ENTRANCE_STRING = 'I'
  private val EXIT_STRING = 'O'

  private val stringRows = pattern.split("\n")

  val width = stringRows.head.length
  val height = stringRows.size

  lazy val walls:Set[Cell] = {
    searchMaze(WALL_STRINGS)
  }

  lazy val paths:Set[Cell] = {
    searchMaze(PATH_STRINGS)
  }

  lazy val entrance:(Cell) = {
    searchMaze(Set(ENTRANCE_STRING)).head
  }

  lazy val exit:(Cell) = {
    searchMaze(Set(EXIT_STRING)).head
  }

  private def searchMaze(chars:Set[Char]) = {
    val rows = stringRows
    val results = for {
      (rowStr:String, row:Int) <- rows.zipWithIndex
      (char:Char, col:Int) <- rowStr.zipWithIndex
      if chars.contains(char)
    } yield new Cell(col, row)

    results.toSet
  }

}

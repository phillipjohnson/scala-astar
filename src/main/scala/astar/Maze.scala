package astar

/**
 * Author: Phillip Johnson
 * Date: 3/14/15
 */
class Maze(val pattern:String) {

  private val WALL_STRINGS = Set('+','-','|')
  private val PATH_STRINGS = Set(' ','I','O')
  private val ENTRANCE_STRING = 'I'
  private val EXIT_STRING = 'O'

  private val stringRows = pattern.split("\n")

  val width = stringRows.head.length
  val height = stringRows.size

  lazy val walls:Set[(Int,Int)] = {
    searchMaze(WALL_STRINGS)
  }

  lazy val paths:Set[(Int,Int)] = {
    searchMaze(PATH_STRINGS)
  }

  lazy val entrance:(Int,Int) = {
    searchMaze(Set(ENTRANCE_STRING)).head
  }

  lazy val exit:(Int,Int) = {
    searchMaze(Set(EXIT_STRING)).head
  }

  private def searchMaze(chars:Set[Char]) = {
    val rows = stringRows
    val results = for {
      (rowStr:String, row:Int) <- rows.zipWithIndex
      (char:Char, col:Int) <- rowStr.zipWithIndex
      if chars.contains(char)
    } yield (col, row)

    results.toSet
  }

}

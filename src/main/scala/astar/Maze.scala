package astar

/**
 * Author: Phillip Johnson
 * Date: 3/14/15
 */
class Maze(val pattern:String) {

  lazy val html:String = ""

  lazy val walls:Set[(Int,Int)] = {
    val rows = pattern.split("\n")
    val wallsList = for {
      (rowStr:String, row:Int) <- rows.zipWithIndex
      (char:Char, col:Int) <- rowStr.zipWithIndex
      if char == '+' || char == '-' || char == '|'
    } yield (col, row)

    wallsList.toSet
  }

  lazy val paths:Set[(Int,Int)] = {
    val rows = pattern.split("\n")
    val pathList = for {
      (rowStr:String, row:Int) <- rows.zipWithIndex
      (char:Char, col:Int) <- rowStr.zipWithIndex
      if char == ' ' || char == 'I' || char == 'O'
    } yield (col, row)

    pathList.toSet
  }

}

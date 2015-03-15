package astar.maze

/**
 * Author: Phillip Johnson
 * Date: 3/15/15
 */
class Cell(val x:Int, val y:Int) {



  override def toString = "(" + x + ", " + y + ")"

  def canEqual(other: Any): Boolean = other.isInstanceOf[Cell]

  override def equals(other: Any): Boolean = other match {
    case that: Cell =>
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

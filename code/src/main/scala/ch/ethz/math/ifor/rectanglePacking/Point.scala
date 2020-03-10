package ch.ethz.math.ifor.rectanglePacking

abstract class Point(val coordinates: Vector[Double]) {
  assert(coordinates.length == dimension)

  /*
  this is a generic comment
   */
  def dominates(point: Point): Boolean = (coordinates zip point.coordinates).forall(pair => pair._1 < pair._2)

  //TODO: check if there is a built-in function for p-norms that handles inf.
  def norm(p: Double) = {
    assert(p > 0)
    if (p == inf) {
      coordinates.map(_.abs).max
    }
    else {
      Math.pow(coordinates.map(a => Math.pow(a.abs, p)).sum, 1 / p)
    }
  }

  /**
    * Returns the distance to a shift point (p-norm)
    * @param shift
    * @param p
    * @return
    */
  def normShift(shift: Point,p: Double): Double = {
    assert(p > 0)
    if (p == inf) {
      (coordinates zip shift.coordinates).map(pair => pair._1 - pair._2).map(_.abs).max
    }
    else {
      Math.pow((coordinates zip shift.coordinates).map(pair => pair._1 - pair._2).map(a => Math.pow(a.abs, p)).sum, 1 / p)
    }
  }

  //def diff(pt:Point): Point = {
   // new Point((coordinates zip pt.coordinates).map(pair => pair._1 - pair._2))
 // }

}


object Point {

  /** origin and topright are the points (0,0) and (1,1) wrt. the original square instance
    * warning: origin and topright are hard-coded points and do not refer to an instance
    */
  val origin: BoundaryPoint = new BoundaryPoint(Vector(0, 0))
  val topright: BoundaryPoint = new BoundaryPoint(Vector(1, 1))

}
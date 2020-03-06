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

}


object Point {

  /** origin and topright are the points (0,0) and (1,1) wrt. the original square instance
    * warning: origin and topright are hard-coded points and do not refer to an instance
    */
  val origin: BoundaryPoint = new BoundaryPoint(Vector(0, 0))
  val topright: BoundaryPoint = new BoundaryPoint(Vector(1, 1))

}
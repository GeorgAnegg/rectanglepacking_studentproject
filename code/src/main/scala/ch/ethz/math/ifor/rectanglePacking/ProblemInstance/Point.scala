package ch.ethz.math.ifor.rectanglePacking.ProblemInstance

import ch.ethz.math.ifor.rectanglePacking.{dimension, inf}

class Point(val coordinates: Vector[Double]) {
  require(coordinates.length == dimension)

  /** point.subtract(shift) computes point-shift
    *
    * @param point
    * @return
    */
  def subtract (point: Point): Point = new Point( (coordinates,point.coordinates).zipped.map(_-_))

  def dominates(point: Point): Boolean = (coordinates zip point.coordinates).forall(pair => pair._1 < pair._2)


  /** Computes the distance to the shift point in the p-norm
    *
    * @param p     : Used in the p-norm
    * @param shift : Default top-right. To compute the usual norm, use Point.origin
    * @return
    */

  def normS(p: Double, shift: Point = Point.topright): Double = {
    require(p > 0)
    if (p == inf) {
      subtract(shift).coordinates.map(_.abs).max
    }
    else {
      Math.pow(subtract(shift).coordinates.map(a => Math.pow(a.abs, p)).sum, 1 / p)
    }
  }

}


object Point {


  def compare(p: Double, shift: Point): (Point, Point) => Boolean = {
    (point1: Point, point2: Point) =>
      point1.normS(p, shift) < point2.normS(p, shift)
  }


  /** origin and topright are the points (0,0) and (1,1) wrt. the original square instance
    * warning: origin and topright are hard-coded points and do not refer to a general instance or dimension
    */
  val origin: BoundaryPoint = BoundaryPoint(Vector(0, 0))
  val topright: BoundaryPoint = BoundaryPoint(Vector(1, 1))

}
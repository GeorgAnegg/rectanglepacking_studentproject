package ch.ethz.math.ifor.rectanglePacking.problemInstance

import ch.ethz.math.ifor.rectanglePacking.{Rectangle, dimension, inf}

class Point(val coordinates: Vector[Double]) {
  require(coordinates.length == dimension,"Wrong number of coordinates in a point")

  /** adds a small random perturbation to Point
    * random pertubation is ~N(0,sigma)
    * @return perturbed point
    */
  def perturb(sigma: Double): Point= ???

  /** point.subtract(shift) computes point-shift
    *
    * @param point
    * @return
    */
  def subtract (point: Point): Point = new Point( (coordinates,point.coordinates).zipped.map(_-_))

  /** True if the argument dominates loose the point (coordinates >=)
    *
    * @param point
    * @return
    */
  def dominatesLoose(point: Point): Boolean = (coordinates zip point.coordinates).forall(pair => pair._1 <= pair._2)

  /** True if the argument dominates strictly the point (coordinates >)
    *
    * @param point
    * @return
    */
  def dominatesStrict(point: Point): Boolean = (coordinates zip point.coordinates).forall(pair => pair._1 < pair._2)

  /** Computes the distance to the shift point in the p-norm
    *
    * @param p     : Used in the p-norm
    * @param shift : Default top-right. To compute the usual norm, use Point.origin
    * @return
    */

  def normS(p: Double, shift: Point = Point.topright): Double = {
    require(p > 0,"Wrong p for the norm")
    if (p == inf) {
      subtract(shift).coordinates.map(_.abs).max
    }
    else {
      Math.pow(subtract(shift).coordinates.map(a => Math.pow(a.abs, p)).sum, 1 / p)
    }
  }

  /** Returns true if the point is in the rectangle (boundaries included)
    *
    * @param r
    * @return
    */
  def inRectLoose(r:Rectangle):Boolean = {
    r.originCorner.dominatesLoose(this) && this.dominatesLoose(r.topRightCorner)
  }

  /** Returns true if the point is inside the rectangle (boundaries excluded)
    *
    * @param r
    * @return
    */
  def inRectStrict(r:Rectangle): Boolean = {
    r.originCorner.dominatesStrict((this)) && this.dominatesStrict(r.topRightCorner)
  }

  /** Returns true if the point is in the rectangle (upper boundaries excluded)
    *
    * @param r
    * @return
    */
  def inRectStrictUP(r:Rectangle): Boolean = {
    r.originCorner.dominatesLoose(this) && this.dominatesStrict(r.topRightCorner) && r.originCorner.coordinates!=coordinates
  }

}


object Point {

  /** Compares distance between two points to the shift with p-norm
    *
    * @param p
    * @param shift
    * @return
    */
  def compare(p: Double, shift: Point): (Point, Point) => Boolean = {
    (point1: Point, point2: Point) =>
      point1.normS(p, shift) < point2.normS(p, shift)
  }


  /** origin and topright are the points (0,0) and (1,1) wrt. the original square instance
    * warning: origin and topright are hard-coded points and do not refer to a general instance or dimension
    */
  val origin: Point = new Point(Vector.fill(dimension)(0))
  val topright: Point = new Point(Vector.fill(dimension)(1))

}
package ch.ethz.math.ifor.rectanglePacking.ProblemInstance

import ch.ethz.math.ifor.rectanglePacking.{dimension,inf}

abstract class Point(val coordinates: Vector[Double]) {
  require(coordinates.length == dimension)


  def dominates(point: Point): Boolean = (coordinates zip point.coordinates).forall(pair => pair._1 < pair._2)

  //TODO: check if there is a built-in function for p-norms that handles inf.

  //TODO: only have single function norm with shift=topright as default variable.
  def norm(p: Double) = {
    require(p > 0)
    if (p == inf) {
      coordinates.map(_.abs).max
    }
    else {
      Math.pow(coordinates.map(a => Math.pow(a.abs, p)).sum, 1 / p)
    }
  }

  /**
    * Returns the distance to a shift point (p-norm)
    */
  def normShift(p: Double, shift: Point): Double = {
    require(p > 0)
    if (p == inf) {
      (coordinates zip shift.coordinates).map(pair => pair._1 - pair._2).map(_.abs).max
    }
    else {
      Math.pow((coordinates zip shift.coordinates).map(pair => pair._1 - pair._2).map(a => Math.pow(a.abs, p)).sum, 1 / p)
    }
  }

}


object Point {


  def compare(p:Double, shift:Point): (Point,Point)=>Boolean = {
   (point1: Point, point2: Point)=>
    point1.normShift(p,shift)<point2.normShift(p,shift)
  }


  /** origin and topright are the points (0,0) and (1,1) wrt. the original square instance
    * warning: origin and topright are hard-coded points and do not refer to an instance
    */
  val origin: BoundaryPoint = new BoundaryPoint(Vector(0, 0))
  val topright: BoundaryPoint = new BoundaryPoint(Vector(1, 1))

}
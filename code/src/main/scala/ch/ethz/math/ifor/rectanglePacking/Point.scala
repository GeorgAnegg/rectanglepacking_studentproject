package ch.ethz.math.ifor.rectanglePacking

abstract class Point(val coordinates: Vector[Double]) {
  assert(coordinates.length == dimension)

  def dominates(point: Point): Boolean = (coordinates zip point.coordinates).forall(pair => pair._1 < pair._2)


}
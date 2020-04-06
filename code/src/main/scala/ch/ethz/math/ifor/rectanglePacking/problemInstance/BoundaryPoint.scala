package ch.ethz.math.ifor.rectanglePacking.problemInstance

case class BoundaryPoint(override val coordinates:Vector[Double]) extends Point(coordinates) {

}

object BoundaryPoint {

  /** transforms a Point into a BoundaryPoint */
  def pointToBoundaryPoint(p: Point): BoundaryPoint = BoundaryPoint(p.coordinates)




}
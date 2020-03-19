package ch.ethz.math.ifor.rectanglePacking.ProblemInstance

case class BoundaryPoint(override val coordinates:Vector[Double]) extends Point(coordinates) {

}

object BoundaryPoint {

  /** transforms a Point into a BoundaryPoint */
  def toBoundaryPoint(p: Point): BoundaryPoint = BoundaryPoint(p.coordinates)
}
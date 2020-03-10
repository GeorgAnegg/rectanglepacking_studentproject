package ch.ethz.math.ifor.rectanglePacking.ProblemInstance

class Anchor(coordinates:Vector[Double]) extends Point(coordinates) {

  /**
    * after a rectangle has been picked for this anchor, it becomes a boundary point of a new shape
    */
  def transform: BoundaryPoint = new BoundaryPoint(coordinates)

}

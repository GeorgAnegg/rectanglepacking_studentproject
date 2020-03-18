package ch.ethz.math.ifor.rectanglePacking

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.Anchor
import ch.ethz.math.ifor.rectanglePacking.SegmentGreedy.BoundarySegment

class Rectangle(val anchorOrigin : Anchor, val anchorTopRight: Anchor ) {
  //TODO: Maybe needs new vertex class for topright points of rectangles
  /**
    * For a rectangle in dimension "dimension", returns the low boundary corresponding to the hyperplane on dimension i
    * @param i
    * @return
    */
  def lowBoundary(i : Int): BoundarySegment = {
    require (i>=0 && i< dimension)

    //val boundaryCoordinate = (0 until dimension).filterNot(i==_).map(j=>(anchorOrigin.coordinates(j),anchorTopRight.coordinates(j)))
    var boundaryCoordinates: Vector[Double] = Vector.empty[Double]
    for (j <- 0 until dimension) {
      if (j==i) {
          boundaryCoordinates= boundaryCoordinates :+ anchorOrigin.coordinates(i)
      }
      else {
        boundaryCoordinates = boundaryCoordinates :+ anchorOrigin.coordinates(j)
        boundaryCoordinates = boundaryCoordinates :+ anchorTopRight.coordinates(j)
      }
    }
    new BoundarySegment(boundaryCoordinates)
  }
}

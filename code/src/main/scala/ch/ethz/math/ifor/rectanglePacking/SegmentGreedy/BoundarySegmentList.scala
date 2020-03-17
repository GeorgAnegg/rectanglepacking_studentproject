package ch.ethz.math.ifor.rectanglePacking.SegmentGreedy
import ch.ethz.math.ifor.rectanglePacking.{Rectangle, dimension}

/**
  * Describes the boundaries for the problem : an array on length dimension, and for each dimension, a list of the boundaries
  * in this dimension (in 2D : horizontal and vertical)
  * @param boundarySegmentList
  */
class BoundarySegmentList (val boundarySegmentList : Array[List[BoundarySegment]]) {



  /**
    * adds low boundaries of the segment to the list of boundaries
    * @param rectangle
    */
  def update(rectangle: Rectangle): Unit ={
    require(rectangle.anchorOrigin.dominates(rectangle.anchorTopRight))
    for (i  <- 0 until dimension) {
      boundarySegmentList(i) = boundarySegmentList(i) :+ new BoundarySegment(rectangle.lowBoundary(i))
    }


  }
}

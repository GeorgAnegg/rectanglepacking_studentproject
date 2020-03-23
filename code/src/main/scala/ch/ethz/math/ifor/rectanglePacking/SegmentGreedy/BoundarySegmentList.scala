package ch.ethz.math.ifor.rectanglePacking.SegmentGreedy

import ch.ethz.math.ifor.rectanglePacking.{Rectangle, dimension}

/** Describes the boundaries for the problem
  * an array on length dimension, and for each dimension, a list of the boundaries
  * in this dimension (in 2D : horizontal and vertical)
  * TODO: Check that ordering here matches ordering in greedy iteration
  *
  * @param boundarySegmentList
  */
class BoundarySegmentList(val boundarySegmentList: Array[List[BoundarySegment]]) {
  require(boundarySegmentList.length == dimension)


  /** adds low boundaries of the segment to the list of boundaries
    *
    * @param rectangle
    */
  def update(rectangle: Rectangle): Unit = {
    require(rectangle.originCorner.dominatesStrict(rectangle.topRightCorner))
    for (i <- 0 until dimension) {
      boundarySegmentList(i) = boundarySegmentList(i) :+ rectangle.lowBoundary(i)
    }


  }
}

object BoundarySegmentList {

  def unitSquareBoundary: BoundarySegmentList = ???
    //TODO: write here the boundarySegmentList for the unit square instance

}

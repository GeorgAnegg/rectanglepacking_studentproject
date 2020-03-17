package ch.ethz.math.ifor.rectanglePacking.SegmentGreedy
import ch.ethz.math.ifor.rectanglePacking.dimension

class BoundarySegment(val SegmentCoordinates : Vector[Double]) {
  /**
    * represents a face with two coordinates for each dimension, except the one of the hyperplane
    * Ex : for a horizontal segment in 2D : (x1,x2,y)
    * */
  require(SegmentCoordinates.length==2*dimension-1)


}

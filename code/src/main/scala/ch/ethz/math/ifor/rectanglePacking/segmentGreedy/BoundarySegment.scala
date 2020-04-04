package ch.ethz.math.ifor.rectanglePacking.SegmentGreedy
import ch.ethz.math.ifor.rectanglePacking.dimension


//TODO: let's change the way we look at boundaries. I would like to consider them as having a root and expanding from there in each dimension (which makes up the segments).
// then a segment only consists of a 'root/base point' and an additional point in each direction. we can compute the segments out of that information if we have to
// this seems cleaner to me.
// I'd probably rename this class RootedSegments and give it constructors (root: BoundaryPoint, extension: Map[ Int, BoundaryPoint])
// BoundarySegmentList can stay, but it will now contain RootedSegments
class BoundarySegment(val SegmentCoordinates : Vector[Double]) {
  /** represents a face with two coordinates for each dimension, except the one of the hyperplane
    * Ex : for a horizontal segment in 2D : (x1,x2,y)
    * */
  require(SegmentCoordinates.length==2*dimension-1,"Boundary segment representation wrong")


}

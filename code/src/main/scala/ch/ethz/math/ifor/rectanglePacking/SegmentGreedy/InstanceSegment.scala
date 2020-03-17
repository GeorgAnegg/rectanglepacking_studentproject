package ch.ethz.math.ifor.rectanglePacking.SegmentGreedy


import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.{Anchor, Point}


class InstanceSegment (val boundarySegments : BoundarySegmentList, val anchors:Vector[Anchor]) {
  def normSort(p:Double, shift:Point=Point.topright): InstanceSegment= new InstanceSegment(boundarySegments, anchors.sortWith(Point.compare(p, shift)))



  def randomSort: InstanceSegment = ???

  /** this takes as input a permutation of the indices of the anchors and gives an instance with that ordering
    *
    * @param list
    * @return
    */
  def customSort(list: List[Int]): InstanceSegment = ???

}
object InstanceSegment {
  def createRandomInstanceSegment(boundarySegments : BoundarySegmentList,n:Int) :InstanceSegment={
    new InstanceSegment(boundarySegments,(Anchor.random(n-1) :+ new Anchor(Point.origin.coordinates)).toVector)
  }
}

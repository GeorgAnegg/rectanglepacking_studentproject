package ch.ethz.math.ifor.rectanglePacking


class Instance (val boundaryPoints:List[BoundaryPoint], val anchors:Vector[Anchor]) {


  //is this useful to have?
  def sortedAnchors(ordering: Ordering): Vector[Anchor]= ???
}

package ch.ethz.math.ifor.rectanglePacking

/*
Comments:
-if we process points in an ordering that respects domination, our we will not create holes with points in them
-if we make sure that rectangles are always chosen s.t. they do not contain other anchors, we only need to keep track of boundary segments, not of inside/outside.
 */

class Instance (val boundaryPoints:List[BoundaryPoint], val anchors:Vector[Anchor]) {

}

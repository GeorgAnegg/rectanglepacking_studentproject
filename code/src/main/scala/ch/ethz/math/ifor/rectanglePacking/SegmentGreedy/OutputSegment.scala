package ch.ethz.math.ifor.rectanglePacking.SegmentGreedy

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.Anchor
import ch.ethz.math.ifor.rectanglePacking.Rectangle


class OutputSegment(val instance: InstanceSegment, var rectangles : Map[Anchor,Rectangle]) {

  //to make sure that the Map rectangles uses the anchors of the instance
  assert(rectangles.keySet == instance.anchors.toSet)

}

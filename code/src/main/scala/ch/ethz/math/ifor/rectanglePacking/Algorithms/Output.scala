package ch.ethz.math.ifor.rectanglePacking.Algorithms

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.{Anchor, Instance}
import ch.ethz.math.ifor.rectanglePacking.Rectangle

class Output(instance: Instance, rectangles:Map[Anchor,Rectangle]) {

  //to make sure that the Map rectangles uses the anchors of the instance
  assert(rectangles.keySet == instance.anchors.toSet)

}

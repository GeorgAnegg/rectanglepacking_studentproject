package ch.ethz.math.ifor.rectanglePacking

class Output(instance: Instance, rectangles:Map[Anchor,Rectangle]) {

  //to make sure that the Map rectangles uses the anchors of the instance
  assert(rectangles.keySet == instance.anchors.toSet)

}

package ch.ethz.math.ifor.rectanglePacking.algorithms


import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Anchor, Instance}

import ch.ethz.math.ifor.rectanglePacking.Rectangle
import java.io.File

class Output(val instance: Instance, val rectangles: Map[Anchor, Rectangle]) {
  //to make sure that the Map rectangles uses the anchors of the instance
  require(rectangles.keySet == instance.anchors.toSet,"Output keys wrong")

  val uuid: String = java.util.UUID.randomUUID.toString


  // This is the value of the volumes of rectangles
  def objectiveValue: Double = rectangles.values.map(_.volume).sum

}

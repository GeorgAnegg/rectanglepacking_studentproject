package ch.ethz.math.ifor.rectanglePacking.Algorithms

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.{Anchor, Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.Rectangle

object Greedy extends Algorithm {


  def greedyIteration(instance: Instance): (Instance, Rectangle) = ???
  // in here we'll use val currentAnchor :: newAnchors = boundedInstance.anchors (this removes the first element)
  // the anchors of the new instance will be newAnchors
  // TODO: implement this once structure is complete


  def run(instance: Instance): Output = {


    //TODO: wrote it this way because bruteforce will look similar and we can reuse a lot of greedyIteration for it
    // this might not be a good idea

    // pick rectangle for first anchor in instance
    val (newInstance, rectangle) = greedyIteration(instance)

    // recurse. run greedy on remaining Instance
    val internalOutput:Output = Greedy.run(newInstance)

    // the rectangles picked in the remaining instance
    val rectanglesMap = internalOutput.rectangles

    // put the rectangles from the remaining instance together with the current one
    new Output(instance, rectanglesMap + (instance.anchors.head -> rectangle))
  }

}

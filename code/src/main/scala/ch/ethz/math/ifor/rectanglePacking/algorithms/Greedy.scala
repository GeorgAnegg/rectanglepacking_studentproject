package ch.ethz.math.ifor.rectanglePacking.algorithms

import ch.ethz.math.ifor.rectanglePacking.problemInstance.Instance


object Greedy extends Algorithm {
  /** Runs greedy algorithm
    *
    * @param instance
    * @return
    */
  def run(instance: Instance):Output={
    require(instance.forbiddenRectangles.forall(r=>r.contained(instance.topRightBox)),"Forbidden rectangles not in the box")
    val possibleTops=instance.tops()
    GeneralGreedy.auxiliaryRun(instance,possibleTops,tilePacking = false)
  }

/*  def run(instance: Instance): Output = {


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
  }*/

}

package ch.ethz.math.ifor.rectanglePacking.algorithms

import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Anchor, Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.Rectangle

object GeneralGreedy {

  /** Looks for maximal rectangle for a given anchor
    *
    * @param intAnchor
    * @param instance
    * @param possibleTops
    * @return
    */
  def anchorIt(intAnchor:Anchor,instance:Instance,possibleTops:List[Point]): Rectangle = {
    if (possibleTops.isEmpty) {
      Rectangle(intAnchor,intAnchor)
    }
    else {
      val rectIt=anchorIt(intAnchor,instance,possibleTops.tail)
      val pHead=possibleTops.head
      if(intAnchor.dominatesStrict(pHead) && instance.anchors.forall(a=> !(a.inRectStrictUP(Rectangle(intAnchor,pHead)))) && instance.forbiddenRectangles.forall(r=> !(r.intersects(Rectangle(intAnchor,pHead)))) && Rectangle(intAnchor,pHead).volume>rectIt.volume) {
        Rectangle(intAnchor, pHead)
      }
      else{
        rectIt
      }
    }
  }

  /** Iteration for greedy algorithm
    *
    * @param instance
    * @param possibleTops
    * @param tilePacking
    * @return
    */
  def greedyIteration(instance: Instance,possibleTops:List[Point],tilePacking: Boolean): (Instance,Rectangle) = {
    val rectIt=anchorIt(instance.anchors.head,new Instance(instance.topRightBox,instance.forbiddenRectangles,instance.anchors.tail),possibleTops)
    if (tilePacking) {
      val forbiddenRect= Rectangle(rectIt.originCorner,instance.topRightBox)
      (new Instance(instance.topRightBox,forbiddenRect.updateRectanglesTilePacking(instance.forbiddenRectangles),instance.anchors.tail),rectIt)
    }
    else{
      (new Instance(instance.topRightBox,rectIt::instance.forbiddenRectangles,instance.anchors.tail),rectIt)
    }

  }

  // in here we'll use val currentAnchor :: newAnchors = boundedInstance.anchors (this removes the first element)
  // the anchors of the new instance will be newAnchors
  /** Auxiliary function for greedy algorithm to do the recursion
    *
    * @param instance
    * @param possibleTops
    * @param tilePacking
    * @return
    */
  def auxiliaryRun(instance: Instance, possibleTops: List[Point],tilePacking: Boolean):Output = {
    if (instance.anchors.length>1) {
      greedyIteration(instance,possibleTops,tilePacking)
      // pick rectangle for first anchor in instance
      val (newInstance, rectangle) = greedyIteration(instance,possibleTops,tilePacking)
      // recurse. run greedy on remaining Instance
      val internalOutput:Output = auxiliaryRun(newInstance,possibleTops,tilePacking)
      // the rectangles picked in the remaining instance
      val rectanglesMap = internalOutput.rectangles
      // put the rectangles from the remaining instance together with the current one
      new Output(instance, rectanglesMap + (instance.anchors.head -> rectangle))
    }
    else {
      val (newInstance,rectangle)=greedyIteration(instance,possibleTops,tilePacking)
      new Output(instance,Map(instance.anchors.head->rectangle))
    }
  }

}

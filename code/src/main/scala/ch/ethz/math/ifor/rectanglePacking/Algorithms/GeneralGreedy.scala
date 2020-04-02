package ch.ethz.math.ifor.rectanglePacking.Algorithms

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.{Anchor, Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.Rectangle

object GeneralGreedy {

  def anchorIt(intAnchor:Anchor,instance:Instance,possibleTops:List[Point]): Rectangle = {
    if (possibleTops.isEmpty) {
      new Rectangle(intAnchor,intAnchor)
    }
    else {
      val rectIt=anchorIt(intAnchor,instance,possibleTops.tail)
      val pHead=possibleTops.head
      if(intAnchor.dominatesStrict(pHead) && instance.anchors.forall(a=> !(a.inRectStrictUP(new Rectangle(intAnchor,pHead)))) && instance.forbiddenRectangles.forall(r=> !(r.intersects(new Rectangle(intAnchor,pHead)))) && new Rectangle(intAnchor,pHead).volume>rectIt.volume) {
        new Rectangle(intAnchor, pHead)
      }
      else{
        rectIt
      }
    }
  }


  def greedyIteration(instance: Instance,possibleTops:List[Point],tilePacking: Boolean): (Instance,Rectangle) = {
    val rectIt=anchorIt(instance.anchors.head,new Instance(instance.topRightBox,instance.forbiddenRectangles,instance.anchors.tail),possibleTops)
    if (tilePacking) {
      val forbiddenRect=new Rectangle(rectIt.originCorner,instance.topRightBox)
      (new Instance(instance.topRightBox,forbiddenRect::instance.forbiddenRectangles,instance.anchors.tail),rectIt)
    }
    else{
      (new Instance(instance.topRightBox,rectIt::instance.forbiddenRectangles,instance.anchors.tail),rectIt)
    }

  }

  // in here we'll use val currentAnchor :: newAnchors = boundedInstance.anchors (this removes the first element)
  // the anchors of the new instance will be newAnchors

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

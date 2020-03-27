package ch.ethz.math.ifor.rectanglePacking.Algorithms

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.{Anchor, Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.Rectangle

object Greedy extends Algorithm {

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


  def greedyIteration(instance: Instance,possibleTops:List[Point]): (Instance,Rectangle) = {
    val rectIt=anchorIt(instance.anchors.head,new Instance(instance.topRightBox,instance.forbiddenRectangles,instance.anchors.tail),possibleTops)
    println(rectIt)
    (new Instance(instance.topRightBox,rectIt::instance.forbiddenRectangles,instance.anchors.tail),rectIt)
  }

  // in here we'll use val currentAnchor :: newAnchors = boundedInstance.anchors (this removes the first element)
  // the anchors of the new instance will be newAnchors

  def runInt(instance: Instance,possibleTops: List[Point]):Output = {
    if (instance.anchors.length>1) {
      greedyIteration(instance,possibleTops)
      // pick rectangle for first anchor in instance
      val (newInstance, rectangle) = greedyIteration(instance,possibleTops)
      // recurse. run greedy on remaining Instance
      val internalOutput:Output = Greedy.runInt(newInstance,possibleTops)
      // the rectangles picked in the remaining instance
      val rectanglesMap = internalOutput.rectangles
      // put the rectangles from the remaining instance together with the current one
      new Output(instance, rectanglesMap + (instance.anchors.head -> rectangle))
    }
    else {
      val (newInstance,rectangle)=greedyIteration(instance,possibleTops)
      new Output(instance,Map(instance.anchors.head->rectangle))
    }
  }

  def run(instance: Instance):Output={
    require(instance.forbiddenRectangles.forall(r=>r.contained(instance.topRightBox)))
    val possibleTops=instance.tops()
    runInt(instance,possibleTops)
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

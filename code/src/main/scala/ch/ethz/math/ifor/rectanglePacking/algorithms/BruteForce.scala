package ch.ethz.math.ifor.rectanglePacking.algorithms

import ch.ethz.math.ifor.rectanglePacking.Rectangle
import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Anchor, Instance, Point}

object BruteForce extends Algorithm {

  /** Compute the  optimum for one tree
    *
    * @param instance
    * @param possibleTops
    * @param allAnchors
    * @return
    */
  def runDepth(instance: Instance, possibleTops:List[Point], allAnchors:List[Anchor]):Map[Anchor,Rectangle] = {
    if (instance.anchors.length > 1) {
      runBreadth(instance, possibleTops, possibleTops,allAnchors )
    }
    else {
      val rect = GeneralGreedy.anchorIt(instance.anchors.head, new Instance(instance.topRightBox, instance.forbiddenRectangles,allAnchors), possibleTops)
      Map(instance.anchors.head -> rect)
    }
  }

  /** For one anchor, goes through all the possible choices of topright for this anchor (breadth in the graph)
    *
    * @param instance
    * @param choices choices for this anchor (breadth that still has to be treated)
    * @param possibleTops
    * @param allAnchors
    * @return
    */
  def runBreadth(instance: Instance, choices:List[Point], possibleTops:List[Point], allAnchors:List[Anchor]):Map[Anchor,Rectangle]={

    if (choices.length>=1) {
      if (instance.anchors.head.dominatesStrict(choices.head) && allAnchors.forall(a=> !(a.inRectStrictUP(Rectangle(instance.anchors.head,choices.head)))) && instance.forbiddenRectangles.forall(r=> !(r.intersects(Rectangle(instance.anchors.head,choices.head)))) ) {
        val newInst=new Instance(instance.topRightBox,new Rectangle(instance.anchors.head,choices.head)+:instance.forbiddenRectangles,instance.anchors.tail)
        val r1=runDepth(newInst,possibleTops,allAnchors)
        val r2=runBreadth(instance,choices.tail,possibleTops,allAnchors)
        if (r2.values.map(r=>r.volume).sum>(r1.values.map(r=>r.volume).sum+Rectangle(instance.anchors.head,choices.head).volume)) {
          r2
        }

        else {
          r1 + (instance.anchors.head-> new Rectangle(instance.anchors.head,choices.head))
        }
      }
      else {
        runBreadth(instance,choices.tail,possibleTops,allAnchors)
      }
    }
    else {
      val newInst=new Instance(instance.topRightBox,new Rectangle(instance.anchors.head,instance.anchors.head)+:instance.forbiddenRectangles,instance.anchors.tail)
      runDepth(newInst,possibleTops,allAnchors)+ (instance.anchors.head-> new Rectangle(instance.anchors.head,instance.anchors.head))
    }
  }


  def run(instance:Instance):Output=  {
    require(instance.forbiddenRectangles.forall(r=>r.contained(instance.topRightBox)),"Forbidden rectangles not in the box")
    val possibleTops=instance.tops()
    new Output(instance,runDepth(instance,possibleTops,instance.anchors))
  }

}
//TODO: can we use parts of the implemenation of greedy?

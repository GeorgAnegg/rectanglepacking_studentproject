package ch.ethz.math.ifor.rectanglePacking.algorithms

import ch.ethz.math.ifor.rectanglePacking.Rectangle
import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Anchor, Instance, Point}

object BruteForce extends Algorithm {

  def preprocess(instance: Instance): (Instance, Map[Anchor, Rectangle]) = {
    // get list of points in top right area (that are not dominated by any other anchor
    val firstLayerAnchors = instance.anchors.filter(anchor => instance.anchors.forall(!anchor.dominatesStrict(_)))
    val otherAnchors = instance.anchors.filter(!firstLayerAnchors.contains(_))
    assert(firstLayerAnchors.length + otherAnchors.length == instance.anchors.length)
    // sort by x-coordinate
    val firstLayerOrdered = firstLayerAnchors.sortBy(_.coordinates(0))

    // for those anchors, choose max rectangle
    // this only works for the standard 0,1 box instance
    var preRectangles: Map[Anchor, Rectangle] = Map()
    var currentY = instance.topRightBox.coordinates(1)
    for (i<- firstLayerOrdered.indices) {
      val currentAnchor = firstLayerOrdered(i)
      val currentRectangle = Rectangle(currentAnchor, new Point(Vector(1.0, currentY)))
      currentY = currentAnchor.coordinates(1)
      preRectangles+= currentAnchor->currentRectangle
    }
    val forbiddenRectangles = preRectangles.values.toList
    val newInst = new Instance(instance.topRightBox, forbiddenRectangles, otherAnchors)
    (newInst, preRectangles)
  }



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


  def run(instance:Instance, withPreprocess: Boolean = false):Output=  {
    if (!withPreprocess) //without preprocessing (default behaviour)
    {
      require(instance.forbiddenRectangles.forall(r => r.contained(instance.topRightBox)), "Forbidden rectangles not in the box")
      val possibleTops = instance.tops()
      new Output(instance, runDepth(instance, possibleTops, instance.anchors))
    }
    else {//with preprocessing:
      val (newInst, preRectangles) = preprocess(instance)
      val partialOutput = run(newInst)
      val newOutput = new Output(instance, partialOutput.rectangles ++ preRectangles)
      newOutput
    }
  }

}

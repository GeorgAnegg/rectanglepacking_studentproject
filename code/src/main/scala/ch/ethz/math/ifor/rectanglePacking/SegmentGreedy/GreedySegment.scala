package ch.ethz.math.ifor.rectanglePacking.SegmentGreedy

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.{Anchor, Point}
import ch.ethz.math.ifor.rectanglePacking.Rectangle


class GreedySegment(instanceSegment: InstanceSegment,p: Int,shift:Point=Point.topright, outputSegment: OutputSegment) extends AlgorithmSegment(instanceSegment, outputSegment) {

}


object GreedySegment {

  def greedyIteration(instanceSegment: InstanceSegment, anchor: Anchor): Anchor = {
    new Anchor(Vector(1,1))
  }
  //(instance/shape, (anchor, rectangle))

  def runGreedy(instanceSegment: InstanceSegment, p:Int, shift:Point = Point.topright): OutputSegment = {
    val instanceSorted=instanceSegment.normSort(p)
    val List_rectangles= new OutputSegment(instanceSorted,Map.empty[Anchor,Rectangle])

    for(i <- instanceSorted.anchors.indices){
      val res=  new Rectangle(instanceSorted.anchors(i),greedyIteration(instanceSorted,instanceSorted.anchors(i)))
      List_rectangles.rectangles += ( instanceSorted.anchors(i) -> res)
      instanceSorted.boundarySegments.update(res)
    }
    List_rectangles
  }

}
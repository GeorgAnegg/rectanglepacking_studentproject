package ch.ethz.math.ifor.rectanglePacking.ProblemInstance

import ch.ethz.math.ifor.rectanglePacking.SegmentGreedy.BoundarySegmentList


class Instance (val boundaryPoints:BoundarySegmentList, val anchors:List[Anchor]) {

  def normSort(p:Double, shift:Point=Point.topright): Instance= new Instance(boundaryPoints, anchors.sortWith(Point.compare(p, shift)))



  def randomSort: Instance = ???

  /** this takes as input a permutation of the indices of the anchors and gives an instance with that ordering
    *
    * @param list
    * @return
    */
  def customSort(list: List[Int]): Instance = ???

}
object Instance {

  def createRandomUnitSquareInstance(n:Int) ={
    new Instance(BoundarySegmentList.unitSquareBoundary, Anchor.random(n-1) :+ Anchor.pointToAnchor(Point.origin))
  }
}

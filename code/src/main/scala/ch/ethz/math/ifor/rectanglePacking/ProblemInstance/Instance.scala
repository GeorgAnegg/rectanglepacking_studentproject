package ch.ethz.math.ifor.rectanglePacking.ProblemInstance

import ch.ethz.math.ifor.rectanglePacking.{Rectangle, cross, dimension, inf}
import ch.ethz.math.ifor.rectanglePacking.SegmentGreedy.BoundarySegmentList

class Instance (val topRightBox:Point, val forbiddenRectangles:List[Rectangle], val anchors:List[Anchor]) {
  //require(anchors.contains(Point.origin))
  require(forbiddenRectangles.forall(r=>r.volume>0))


  def pointIn(anchor: Anchor): Boolean = {
    anchor.inRectLoose(new Rectangle(Point.origin,topRightBox)) && forbiddenRectangles.forall(r => !(anchor.inRectStrictUP(r)))
  }
  require(anchors.forall(a=>this.pointIn(a)))


  /** Creates list of possible top right corners
    *
    * @return
    */
  def tops():List[Point] ={
    (cross.crossJoin[Double]((0 until dimension).map(j=>((anchors.map(a=>a.coordinates(j))++List(topRightBox.coordinates(j))++forbiddenRectangles.map(r=>r.originCorner.coordinates(j))++forbiddenRectangles.map(r=>r.topRightCorner.coordinates(j)))).distinct).toList)).map(l=>new Point(l.toVector)).distinct
  }


  def normSort(p:Double, shift:Point=Point.topright): Instance= new Instance(topRightBox,forbiddenRectangles, anchors.sortWith(Point.compare(p, shift)))

  def randomSort: Instance = ???

  /** this takes as input a permutation of the indices of the anchors and gives an instance with that ordering
    *
    * @param list
    * @return
    */
  def customSort(list: List[Int]): Instance = ???

}
object Instance {
//TODO: Create random Unit Square intance
  def createRandomUnitSquareInstance(n:Int) ={

  }
}

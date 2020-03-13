package ch.ethz.math.ifor.rectanglePacking.ProblemInstance


class Instance (val boundaryPoints:List[BoundaryPoint], val anchors:Vector[Anchor]) {

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
  def createRandomInstance(boundaryPoints : List[BoundaryPoint],n:Int) ={
    new Instance(boundaryPoints,(Anchor.random(n-1) :+ new Anchor(Point.origin.coordinates)).toVector)
  }
}

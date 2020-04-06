package ch.ethz.math.ifor.rectanglePacking.problemInstance

import ch.ethz.math.ifor.rectanglePacking.{Rectangle, cross, dimension}
import scala.util.Random

class Instance(val topRightBox: Point, val forbiddenRectangles: List[Rectangle], val anchors: List[Anchor]) {
  //TODO: this seems to give a require error when run on createRandomUnitSquareInstance. Georg does not know why.
  //require(anchors.contains(Anchor.origin), "origin is not included in anchors")
  require(forbiddenRectangles.forall(r => r.volume >= 0), "Forbidden rectangle with negative volume")

  /** Checks if anchor is in the right region (in the box + not in forbidden rectangles)
    * for initialization of the algorithms, not used through iterations
    *
    * @param anchor
    * @return
    */
  def pointIn(anchor: Anchor): Boolean = {
    anchor.inRectLoose(Rectangle(Point.origin, topRightBox)) && forbiddenRectangles.forall(r => !(anchor.inRectStrict(r)))
  }

  require(anchors.forall(a => this.pointIn(a)), "There is an anchor in the wrong region (out of the box or in a forbidden rectangle)")

  /** Creates list of possible top right corners
    *
    * @return
    */
  def tops(): List[Point] = {
    (cross.crossJoin[Double]((0 until dimension).map(j => ((anchors.map(a => a.coordinates(j)) ++ List(topRightBox.coordinates(j)) ++ forbiddenRectangles.map(r => r.originCorner.coordinates(j)) ++ forbiddenRectangles.map(r => r.topRightCorner.coordinates(j)))).distinct).toList)).map(l => new Point(l.toVector)).distinct
  }

  def normSort(p: Double, shift: Point = Point.topright): Instance = new Instance(topRightBox, forbiddenRectangles, anchors.sortWith(Point.compare(p, shift)))

  def reverse: Instance = new Instance(topRightBox, forbiddenRectangles, anchors.reverse)

  def randomSort: Instance = new Instance(topRightBox, forbiddenRectangles, Random.shuffle(anchors))

  /** Checks if ordering given in instance is correct for tilepacking
    * A point cannot be treated after another point which he dominates
    *
    * @return
    */
  def tilePackingSorted(): Boolean = {
    anchors.combinations(2).toList.forall(p => !(p(0).dominatesLoose(p(1))))
  }

  /** this takes as input a permutation of the indices of the anchors and gives an instance with that ordering
    *
    * @param list
    * @return
    */
  def customSort(list: List[Int]): Instance = ???

}


object Instance {
  def standardSquare(anchors: List[Anchor]): Instance = new Instance(Point.topright, List(), anchors)

  def createRandomUnitSquareInstance(n: Int): Instance = standardSquare(Anchor.origin :: Anchor.random(n - 1))

  def equallySpacedDiagonal(n: Int): Instance = standardSquare(Anchor.equallySpacedDiagonal(n))
}

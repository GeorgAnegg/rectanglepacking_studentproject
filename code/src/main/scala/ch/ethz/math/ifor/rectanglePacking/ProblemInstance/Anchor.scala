package ch.ethz.math.ifor.rectanglePacking.ProblemInstance

import ch.ethz.math.ifor.rectanglePacking.dimension

case class Anchor(override val coordinates:Vector[Double]) extends Point(coordinates) {

  /**
    * after a rectangle has been picked for this anchor, it becomes a boundary point of a new shape
    */
  def transform: BoundaryPoint = BoundaryPoint(coordinates)

}
object Anchor{
  /** transforms a Point into an Anchor */
  def pointToAnchor(p: Point): Anchor = Anchor(p.coordinates)
  val origin = pointToAnchor(Point.origin)

  /** create a random Anchor in the right dimension (draw from [0,1]x[0,1] uniformly at random)
    */
  def randomAnchor: Anchor = new Anchor(
    (for (i<- 1 to dimension) yield math.random).toVector
  )

  /**
    * create a list of random Anchors (draw from [0,1]x[0,1] uniformly at random)
    */
  def random(n: Int): List[Anchor] =
    (for (i<- 1 to n) yield Anchor.randomAnchor).toList
}

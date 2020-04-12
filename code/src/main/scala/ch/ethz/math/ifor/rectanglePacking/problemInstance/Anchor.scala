package ch.ethz.math.ifor.rectanglePacking.problemInstance

import ch.ethz.math.ifor.rectanglePacking.dimension

import scala.util.Random

case class Anchor(override val coordinates: Vector[Double]) extends Point(coordinates) {

  /** after a rectangle has been picked for this anchor, it becomes a boundary point of a new shape
    *
    */
  def transform: BoundaryPoint = BoundaryPoint(coordinates)

  /** adds a small random perturbation to Anchor (and makes sure it stays in the unit square)
    * random pertubation is ~N(0,sigma)
    * @return perturbed point
    */
  def perturb(sigma: Double = 0.1): Anchor= Anchor(coordinates.map(coord => math.max(0,math.min(1,coord+Random.nextGaussian()*sigma))))



}

object Anchor {
  /** transforms a Point into an Anchor */
  def pointToAnchor(p: Point): Anchor = Anchor(p.coordinates)

  val origin: Anchor = pointToAnchor(Point.origin)

  /** create a random Anchor in the right dimension (draw from [0,1]x[0,1] uniformly at random)
    */
  def randomAnchor: Anchor = new Anchor( Vector.fill(dimension)(math.random) )

  /**
    * create a list of random Anchors (draw from [0,1]x[0,1] uniformly at random)
    */
  def random(n: Int): List[Anchor] = List.fill(n)(Anchor.randomAnchor)

  def equallySpacedDiagonal(n: Int): List[Anchor] = (for (i<- 1 until n+1) yield Anchor(Vector.fill(dimension)(i/(n+1).toDouble))).toList.reverse
}

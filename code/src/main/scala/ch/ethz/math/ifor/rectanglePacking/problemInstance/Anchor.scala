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

  def perturbNotOrigin(sigma: Double = 0.1): Anchor= {
    if (coordinates==Point.origin.coordinates) {
      Anchor(coordinates)
    }
    else {
      Anchor(coordinates.map(coord => math.max(0,math.min(1,coord+Random.nextGaussian()*sigma))))
    }
  }
  def reflect2D: Anchor = {assert(coordinates.length ==2); Anchor(coordinates.reverse)}

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

  def randomSpacedDiagonal(n:Int): List[Anchor] = (for (i<- 1 until n+1) yield math.random()).toList.map(i=>Anchor(Vector.fill(dimension)(i)))

  /** For the other diagonal (only for dim=2) */
  def equallySpaceAntiDiagonal(n:Int): List[Anchor] ={
    require(dimension==2)
    (for (i<- 1 until n+1) yield Anchor(Vector(i/(n+1).toDouble,1-i/(n+1).toDouble))).toList

  }

  def randomSpaceAntiDiagonal(n:Int):List[Anchor]={
    require(dimension==2)
    (for (i<- 1 until n+1) yield math.random()).toList.map(i=>Anchor(Vector(i,1-i)))

  }

  /** Creates an anchor in the very lower part of the square */
  def lowAnchor(): Anchor = {
    val a=math.random()
    Anchor(Vector(a,math.max(0,math.min(a,(a/10+Random.nextGaussian()*(a/20))))))
  }
  /** List of low anchors */
  def lowAnchors(n:Int): List[Anchor]={
    require(dimension==2)
    List.fill(n)(lowAnchor())
  }

  def upAnchor(): Anchor = {
    val a=math.random()
    Anchor(Vector(math.max(0,math.min(a,(a/10+Random.nextGaussian()*(a/20)))),a))
  }
  /** List of low anchors */
  def upAnchors(n:Int): List[Anchor]={
    require(dimension==2)
    List.fill(n)(upAnchor())
  }
}

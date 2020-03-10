package ch.ethz.math.ifor.rectanglePacking


abstract class Ordering {

  /** outputs true if point1 < point2
    */
  def compare(point1: Point, point2: Point): Boolean

  //TODO: implement this.
  // Don't know how to do this with the abstract class
  def reverse: Ordering = ??? //new Ordering(newcompare= !oldcompare)
}

object Ordering {

  def orderAnchorPoints(instance: Instance, ordering: Ordering): Vector[Anchor] = ???

}

package ch.ethz.math.ifor.rectanglePacking


abstract class Ordering {

  /** outputs true if point1 < point2
    */
  def compare(point1: Point, point2: Point): Boolean

  //TODO: implement this.
  def reverse: Ordering = ??? //new Ordering(newcompare= !oldcompare)
}

object Ordering {

  def orderAnchorPoints(instance: Instance, ordering: Ordering): Vector[Anchor] = ???

}

package ch.ethz.math.ifor.rectanglePacking


abstract class Ordering {

  /** outputs true if point1 < point2
    */
  def order(point1:Point, point2:Point):Boolean
}

object Ordering {

  def orderAnchorPoints(instance: Instance, ordering: Ordering):Vector[Anchor] = ???

}

package ch.ethz.math.ifor.rectanglePacking

class NormOrdering(val p: Double, val shift: Point = Point.topright) extends Ordering {


  def compare(point1: Point, point2: Point): Boolean = {
    point1.normShift(shift,p)>point2.normShift(shift,p)
  }
  //TODO: implement this taking shift into account
  //TODO: what is the morally correct name for compare.
}

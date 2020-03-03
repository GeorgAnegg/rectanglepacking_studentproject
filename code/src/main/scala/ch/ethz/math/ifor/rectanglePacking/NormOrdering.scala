package ch.ethz.math.ifor.rectanglePacking

class NormOrdering(val p:Double) extends Ordering {



  def order(point1:Point, point2:Point):Boolean = ???
  //TODO: implement order. it should output true if point1<point2
  /*Note that "inf" is also a Double.
  Check if it handle computation for inf or if you need to separate cases with
if (p == inf) {infinity norm}
else {p-norm}
put in a sanity check (with assert) that p is positive.
*/
}

package ch.ethz.math.ifor.rectanglePacking

abstract class Point(val coordinates: Vector[Double]) {
  assert(coordinates.length == dimension)

  def dominates(point: Point): Boolean = (coordinates zip point.coordinates).forall(pair => pair._1 < pair._2)

  def norm(p: Double)=  {
    assert(p>=1)
    if (p==inf) {
      coordinates.map(_ .abs).max
    }
    else {
      Math.pow(coordinates.map(a => Math.pow(a.abs,p)).sum,1/p)
    }
  }

}
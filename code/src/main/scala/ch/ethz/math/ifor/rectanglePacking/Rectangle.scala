package ch.ethz.math.ifor.rectanglePacking

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.Anchor

class Rectangle(val anchorOrigin : Anchor, val anchorTopRight: Anchor ) {

  /**
    * For a rectangle in dimension "dimension", returns the low boundary corresponding to the hyperplane on dimension i
    * @param i
    * @return
    */
  def lowBoundary(i : Int): Vector[Double] = {
    require (i>=0 && i< dimension)
    var emptyVector: Vector[Double] = Vector.empty[Double]
    for (j <- 0 until dimension) {
      if (j==i) {
          emptyVector= emptyVector :+ anchorOrigin.coordinates(i)
      }
      else {
        emptyVector = emptyVector :+ anchorOrigin.coordinates(j)
        emptyVector = emptyVector :+ anchorTopRight.coordinates(j)
      }
    }
    emptyVector
  }
}

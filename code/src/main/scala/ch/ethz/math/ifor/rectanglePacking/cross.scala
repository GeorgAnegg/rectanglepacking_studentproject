package ch.ethz.math.ifor.rectanglePacking

object cross {
  /** Computes the cartesian product to produce possibleTops
    * this will take as input a list of anchors and rectangles and compute all possible intersection points
    * @param list
    * @tparam T
    * @return
    */
  def crossJoin[T](list: List[List[T]]): List[List[T]] =
    list match {
      case xs :: Nil => xs map (List(_))
      case x :: xs => for {
        i <- x
        j <- crossJoin(xs)
      } yield List(i) ++ j
      case Nil => Nil
    }

}

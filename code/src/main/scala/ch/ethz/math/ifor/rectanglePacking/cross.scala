package ch.ethz.math.ifor.rectanglePacking

object cross extends App{
  def crossJoin[T](list: List[List[T]]): List[List[T]] =
    list match {
      case xs :: Nil => xs map (List(_))
      case x :: xs => for {
        i <- x
        j <- crossJoin(xs)
      } yield List(i) ++ j
    }

}

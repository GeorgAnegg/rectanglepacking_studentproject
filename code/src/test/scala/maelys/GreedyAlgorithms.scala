package maelys

import ch.ethz.math.ifor.rectanglePacking.{Point, Greedy, Instance, NormOrdering}


object GreedyAlgorithms extends App {

  //specify an instance here
  val instance: Instance = ???

  //specify an ordering here
  val ordering: NormOrdering = new NormOrdering(1)
  val orderingFromOrigin: NormOrdering = new NormOrdering(1, Point.origin)


  Greedy.runGreedy(instance,ordering)


}

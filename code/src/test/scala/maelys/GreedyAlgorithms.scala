package maelys

import ch.ethz.math.ifor.rectanglePacking.{Greedy, Instance, NormOrdering}


object GreedyAlgorithms extends App {

  //specify an instance here
  val instance:Instance = ???

  //specify an ordering here
  val ordering:NormOrdering = new NormOrdering(1)


  Greedy.runGreedy(instance,ordering)


}

package maelys

import ch.ethz.math.ifor.rectanglePacking.Algorithms.Greedy
import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.Instance


object GreedyAlgorithms extends App {

  //specify an instance here
  val instance: Instance = ???


  val p:Double =1

  Greedy.runGreedy(instance.normSort(p))
  Greedy.runGreedy(instance.randomSort)



}

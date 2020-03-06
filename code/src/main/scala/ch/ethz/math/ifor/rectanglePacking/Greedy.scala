package ch.ethz.math.ifor.rectanglePacking

class Greedy(instance: Instance, output: Output) extends Algorithm(instance, output) {

}


object Greedy {

  //def greedyIteration(instance: Instance, anchor:Anchor) : (instance/shape, (anchor, rectangle))

  def runGreedy(instance: Instance, ordering: Ordering): Output = ???
  // TODO: adjust compare function so that we can use sortBy
  // it should look something like
  // instance.anchors.sortWith(ordering.compare)

}
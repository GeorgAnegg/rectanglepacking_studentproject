package ch.ethz.math.ifor.rectanglePacking.Algorithms

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.Instance


object TilePacking extends Algorithm {

  def run(instance: Instance): Output = {
    require(instance.tilePackingSorted())
    require(instance.forbiddenRectangles.forall(r => r.contained(instance.topRightBox)))
    val possibleTops = instance.tops()
    GeneralGreedy.auxiliaryRun(instance, possibleTops, tilePacking = true)
  }
}
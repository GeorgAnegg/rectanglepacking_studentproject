package ch.ethz.math.ifor.rectanglePacking.algorithms

import ch.ethz.math.ifor.rectanglePacking.problemInstance.Instance


object TilePacking extends Algorithm {
  /** Runs TilePacking algorithm
    *
    * @param instance
    * @return
    */
  def run(instance: Instance): Output = {
    require(instance.tilePackingSorted(),"Wrong order for tilepacking")
    require(instance.forbiddenRectangles.forall(r => r.contained(instance.topRightBox)),"Forbidden Rectangles not in the box")
    val possibleTops = instance.tops()
    GeneralGreedy.auxiliaryRun(instance, possibleTops, tilePacking = true)
  }
}
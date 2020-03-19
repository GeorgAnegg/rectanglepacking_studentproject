package ch.ethz.math.ifor.rectanglePacking.Algorithms

import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.Instance

trait Algorithm {

  def run(instance: Instance): Output

}

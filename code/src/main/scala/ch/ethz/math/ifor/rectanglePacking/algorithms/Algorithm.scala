package ch.ethz.math.ifor.rectanglePacking.algorithms

import ch.ethz.math.ifor.rectanglePacking.problemInstance.Instance

trait Algorithm {

  def run(instance: Instance): Output

}

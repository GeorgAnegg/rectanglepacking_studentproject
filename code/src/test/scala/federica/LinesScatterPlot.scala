/*
package federica

import co.theasi.plotly._

import util.Random

object LinesScatterPlot extends App {

  val xs = (0 until 100)

  // Generate random y
  val ys0 = (0 until 100).map { i => Random.nextDouble }
  val ys1 = ys0.map { _ + 5.0 }
  val ys2 = ys0.map { _ - 5.0 }

  val p = Plot()
    .withScatter(xs, ys0, ScatterOptions().mode(ScatterMode.Marker).name("marker"))
    .withScatter(xs, ys1,
      ScatterOptions()
        .mode(ScatterMode.Marker, ScatterMode.Line)
        .name("line+marker"))
    .withScatter(xs, ys2, ScatterOptions().mode(ScatterMode.Line).name("line"))

  draw(p, "scatter-mode", writer.FileOptions(overwrite=true))

  /*
  val xs = (0 to 1)
  val x0 = (0 to 0)
  val x1 = (1 to 1)
  val ys0 = (0 to 1).map { i => 0 }
  val ys1 = (0 to 1).map { i => 1 }
  val ys = (0 to 1)


  val p = Plot()
    .withScatter(xs, ys0)
    .withScatter(xs, ys1)
    .withScatter(x0, ys)
    .withScatter(x1, ys)



  draw(p, "square", writer.FileOptions(overwrite=true))

   */
}


 */
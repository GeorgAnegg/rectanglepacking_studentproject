package federica

import co.theasi.plotly.{Plot,draw}

object VecTest extends App {
  println{"Hello Federica"}
  val xs: Vector[Double]= Vector(0,0,1,1,0)
  val ys: Vector[Double]= Vector(0,1,1,0,0)

  val x1: Vector[Double]= Vector(0.5,0.5,1)
  val y1: Vector[Double]= Vector(1,0.5,0.5)

  val x: Vector[Vector[Double]] = Vector(xs,x1)
  val y: Vector[Vector[Double]] = Vector(ys,y1)

  var plot = Plot()
  for (i<- 0 until 2){
    plot=plot.withScatter(x(i),y(i))
  }

  //val plot = Plot().withScatter(xs, ys)

  draw(plot, "VecTest")
}

/* Note:
  it takes 10 sec for both 100 or 1000 rectangles but plotly can't open the 1000 rectangles (it doesn't show anything)
  plotly shows graph for 800 rectangles
 */
package federica

import plotly._, element.Fill, layout._, Plotly._

object FillColourTest extends App {

  val r0: Vector[Vector[Double]] = Vector(Vector(0,0,1,1,0), Vector(0,1,1,0,0))
  val r1: Vector[Vector[Double]] = Vector(Vector(0.5,0.5,1,1,0.5), Vector(0.5,1,1,0.5,0.5))
  val r2: Vector[Vector[Double]] = Vector(Vector(0,0.5,0.5,0,0), Vector(0,0,1,1,0))

  val r = Vector(r1,r2)

  var x: Vector[Scatter] = Vector(Scatter(r0(0),r0(1)))
  for (i <- r.indices) {
    x = x :+ Scatter(r(i)(0),r(i)(1),fill=Fill.ToSelf)
  }

  x.plot(
    "outputFiles/prova.html",
    Layout(
      title = "Rectangle Packing",
      showlegend = false,
      height = 600,
      width = 600),
    false,
    true,
    true)

}

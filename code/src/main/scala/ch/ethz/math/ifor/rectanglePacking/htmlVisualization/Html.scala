package ch.ethz.math.ifor.rectanglePacking.htmlVisualization

import ch.ethz.math.ifor.rectanglePacking.algorithms.Output

import plotly._
import element._
import layout._
import Plotly._

object Html {

  def writeHtmlFile(output:Output, nameAlg: String, show: Boolean = false): Unit = {
    val r0: Vector[Vector[Double]] = Vector(Vector(0, 0, 1, 1, 0), Vector(0, 1, 1, 0, 0))

    var r: Vector[Vector[Vector[Double]]] = Vector()
    output.rectangles.foreach {
      case (_, rec) =>
        val x_left: Double = rec.originCorner.coordinates(0)
        val x_right: Double = rec.topRightCorner.coordinates(0)
        val y_bottom: Double = rec.originCorner.coordinates(1)
        val y_top: Double = rec.topRightCorner.coordinates(1)
        val x_new: Vector[Double] = Vector(x_left, x_right, x_right, x_left, x_left)
        val y_new: Vector[Double] = Vector(y_bottom, y_bottom, y_top, y_top, y_bottom)
        r = r :+ Vector(x_new, y_new)
    }

    var plotRectangle: Vector[Scatter] = Vector(Scatter(r0(0), r0(1)))
    for (i <- r.indices) {
      plotRectangle = plotRectangle :+ Scatter(r(i)(0), r(i)(1), fill = Fill.ToSelf)
    }


    plotRectangle.plot(
      path = "outputFiles/htmlFiles/" + nameAlg + output.uuid + ".html",
      Layout(
        title = "Rectangle Packing",
        showlegend = false,
        height = 600,
        width = 600),
      useCdn=false,
      openInBrowser = show,
      addSuffixIfExists=true)

    /* Ancient plotly:
    // This are the coordinates of the original square, maybe should they instead depends on the instance?
    val x0: Vector[Double] = Vector(0, 1, 1, 0, 0)
    val y0: Vector[Double] = Vector(0, 0, 1, 1, 0)

    // Initialize list of Vectors that we are going to plot
    var x: Vector[Vector[Double]] = Vector(x0)
    var y: Vector[Vector[Double]] = Vector(y0)

    // We add every rectangle to the list in order to plot it
    rectangles foreach {
      case (anc, rec) =>
        val x_left: Double = rec.originCorner.coordinates(0)
        val x_right: Double = rec.topRightCorner.coordinates(0)
        val y_bottom: Double = rec.originCorner.coordinates(1)
        val y_top: Double = rec.topRightCorner.coordinates(1)
        val x_new: Vector[Double] = Vector(x_left, x_right, x_right, x_left, x_left)
        val y_new: Vector[Double] = Vector(y_bottom, y_bottom, y_top, y_top, y_bottom)
        x = x :+ x_new
        y = y :+ y_new
    }
    var plot = Plot()
    for (i <- 0 until x.length) {
      plot = plot.withScatter(x(i), y(i))
    }
    draw(plot, "Output")
    */
  }

  // function used for subplots
  def scatterRectangles(output:Output, nameAlg: String, xcoord: AxisReference, ycoord: AxisReference): Vector[plotly.Scatter] = {
    val r0: Vector[Vector[Double]] = Vector(Vector(0, 0, 1, 1, 0), Vector(0, 1, 1, 0, 0))

    var r: Vector[Vector[Vector[Double]]] = Vector()
    output.rectangles.foreach {
      case (_, rec) =>
        val x_left: Double = rec.originCorner.coordinates(0)
        val x_right: Double = rec.topRightCorner.coordinates(0)
        val y_bottom: Double = rec.originCorner.coordinates(1)
        val y_top: Double = rec.topRightCorner.coordinates(1)
        val x_new: Vector[Double] = Vector(x_left, x_right, x_right, x_left, x_left)
        val y_new: Vector[Double] = Vector(y_bottom, y_bottom, y_top, y_top, y_bottom)
        r = r :+ Vector(x_new, y_new)
    }

    var plotRectangle: Vector[plotly.Scatter] = Vector(Scatter(r0(0), r0(1), name=nameAlg, xaxis = xcoord, yaxis = ycoord ))
    for (i <- r.indices) {
      plotRectangle = plotRectangle :+ Scatter(r(i)(0), r(i)(1), name=nameAlg, xaxis = xcoord, yaxis = ycoord, fill = Fill.ToSelf)
    }
    plotRectangle
  }


}

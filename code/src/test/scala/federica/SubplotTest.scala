package federica

import plotly.Scatter
import plotly._
import layout._
import Plotly._
import plotly.element.{AxisAnchor, AxisReference}

object SubplotTest extends App {

  val data = Seq(1, 2)

  val trace1 = Scatter(
    data,
    data,
    //,name = "(1,1)"
    xaxis = AxisReference.X1,
    yaxis = AxisReference.Y1)

  val trace2 = Scatter(
    data,
    data,
    //name = "(1,2)",
    xaxis = AxisReference.X2,
    yaxis = AxisReference.Y2)

  val trace3 = Scatter(
    data,
    data,
    //name = "(1,2)",
    xaxis = AxisReference.X3,
    yaxis = AxisReference.Y3)

  val trace4 = Scatter(
    data,
    data,
    //name = "(1,2)",
    xaxis = AxisReference.X4,
    yaxis = AxisReference.Y4)

  Seq(trace1, trace2, trace3, trace4).plot(
    path = "outputFiles/psubplots.html",
    Layout(
      title = "Mulitple Custom Sized Subplots",
      showlegend = true,
      xaxis = Axis(
        anchor = AxisAnchor.Reference(AxisReference.Y1),
        domain = (0, 0.45)),
      yaxis = Axis(
        anchor = AxisAnchor.Reference(AxisReference.X1),
        domain = (0.5, 1)),
      xaxis2 = Axis(
        anchor = AxisAnchor.Reference(AxisReference.Y2),
        domain = (0.55, 1)),
      yaxis2 = Axis(
        anchor = AxisAnchor.Reference(AxisReference.X2),
        domain = (0.8, 1)),
      xaxis3 = Axis(
        anchor = AxisAnchor.Reference(AxisReference.Y3),
        domain = (0.55, 1)),
      yaxis3 = Axis(
        anchor = AxisAnchor.Reference(AxisReference.X3),
        domain = (0.5, 0.75)),
      xaxis4 = Axis(
        anchor = AxisAnchor.Reference(AxisReference.Y4),
        domain = (0, 1)),
      yaxis4 = Axis(
        anchor = AxisAnchor.Reference(AxisReference.X4),
        domain = (0, 0.45))),
    false,
    false,
    true)


}

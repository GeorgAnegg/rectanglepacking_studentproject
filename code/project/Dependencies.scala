import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val plotly = "co.theasi" %% "plotly" % "0.2.0"
  lazy val poi = "org.apache.poi" % "poi" % "4.0.0"
  lazy val poiOoxml = "org.apache.poi" % "poi-ooxml" % "4.0.0"
  lazy val plotlyRender = "org.plotly-scala" %% "plotly-render" % "0.7.0"
}

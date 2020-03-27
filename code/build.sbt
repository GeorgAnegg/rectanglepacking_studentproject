import Dependencies._

ThisBuild / scalaVersion     := "2.11.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ch.ethz.math.ifor"
ThisBuild / organizationName := "ETH Zurich"

lazy val root = (project in file("."))
  .settings(
    name := "main",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += poi,
    libraryDependencies += poiOoxml,

    //see https://plot.ly/scala/getting-started/
    // need to create file ~/.plotly/.credentials with content
    // {
    //    "username": "DemoAccount",
    //    "api_key": "lr1c37zw81"
    //}
    // where api_key can be seen on your plotly account's profile settings page
    libraryDependencies += plotly
  )


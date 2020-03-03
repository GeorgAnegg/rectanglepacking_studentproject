import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "ch.ethz.math.ifor"
ThisBuild / organizationName := "ETH Zurich"

lazy val root = (project in file("."))
  .settings(
    name := "main",
    libraryDependencies += scalaTest % Test
  )

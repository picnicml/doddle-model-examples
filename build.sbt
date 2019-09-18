
lazy val root = (project in file("."))
  .settings(
    name := "doddle-model-examples",
    organization := "io.github.picnicml",
    homepage := Some(url("https://picnicml.github.io")),
    version := Version(),
    scalaVersion := "2.13.0",
    libraryDependencies ++= Dependencies.settings,
    developers := List(
        Developer("inejc", "Nejc Ilenic", "nejc.ilenic@gmail.com", url("https://github.com/inejc"))
    ),
    licenses := List("MIT" -> url("https://opensource.org/licenses/MIT")),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-unchecked",
      "-Xfatal-warnings",
      "-Xlint",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard"
    )
  )

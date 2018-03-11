
lazy val root = (project in file("."))
  .settings(
    name := "doddle-model-examples",
    organization := "com.picnicml",
    version := "0.0.0",
    scalaVersion := "2.12.4",
    libraryDependencies += "com.picnicml" %% "doddle-model" % "0.0.0"
  )

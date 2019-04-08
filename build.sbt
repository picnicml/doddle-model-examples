
lazy val root = (project in file("."))
  .settings(
    name := "doddle-model-examples",
      organization := "io.github.picnicml",
      homepage := Some(url("https://picnicml.github.io")),
      scmInfo := Some(ScmInfo(
          url("https://github.com/picnicml/doddle-model-examples"),
          "https://github.com/picnicml/doddle-model-examples.git")
      ),
      version := Version(),
      scalaVersion := "2.12.8",
      crossScalaVersions := Seq("2.11.12", "2.12.8"),
      libraryDependencies ++= Dependencies.settings,
      developers := List(
          Developer("inejc", "Nejc Ilenic", "nejc.ilenic@gmail.com", url("https://github.com/inejc"))
      ),
      licenses := List("MIT" -> url("https://opensource.org/licenses/MIT"))
  )

val compilerOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:higherKinds",
  "-Ypartial-unification",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture",
  "-Yno-predef",
  "-Ywarn-unused-import"
)

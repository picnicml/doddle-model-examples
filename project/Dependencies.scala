import sbt._

object Dependencies {

  object V {
    val doodleVersion = "0.0.1-alpha5"
    val slf4jVersion = "1.7.25"
  }

  val compileDependencies: Seq[ModuleID] = Seq(
    "io.github.picnicml" %%  "doddle-model" % V.doodleVersion,
    "org.slf4j" % "slf4j-nop" % V.slf4jVersion
  )

  def settings: Seq[ModuleID] = {
    compileDependencies
  }
}

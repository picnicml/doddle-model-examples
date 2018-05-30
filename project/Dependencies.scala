import sbt._

object Dependencies {

  object V {
    val doodleVersion = "0.0.0-SNAPSHOT"
    val slf4jVersion = "1.7.25"
  }

  val compileDependencies: Seq[ModuleID] = Seq(
    "com.picnicml" %% "doddle-model" % V.doodleVersion,
    "org.slf4j" % "slf4j-nop" % V.slf4jVersion
  )

  def settings: Seq[ModuleID] = {
    compileDependencies
  }
}

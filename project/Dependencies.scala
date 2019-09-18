import sbt._

object Dependencies {

  object DependencyVersion {
    val doodle = "0.0.1-beta4"
    val breeze = "1.0"
    val slf4j = "1.7.26"
  }

  val compileDependencies: Seq[ModuleID] = Seq(
    "io.github.picnicml" %%  "doddle-model" % DependencyVersion.doodle,
    "org.scalanlp" %% "breeze-natives" % DependencyVersion.breeze,
    "org.slf4j" % "slf4j-nop" % DependencyVersion.slf4j
  )

  def settings: Seq[ModuleID] = {
    compileDependencies
  }
}

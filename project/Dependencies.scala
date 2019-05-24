import sbt._

object Dependencies {

  object DependencyVersion {
    val doodleVersion = "0.0.1-beta3"
    val breezeVersion = "0.13.2"
    val slf4jVersion = "1.7.26"
  }

  val compileDependencies: Seq[ModuleID] = Seq(
    "io.github.picnicml" %%  "doddle-model" % DependencyVersion.doodleVersion,
    "org.scalanlp" %% "breeze-natives" % DependencyVersion.breezeVersion,
    "org.slf4j" % "slf4j-nop" % DependencyVersion.slf4jVersion
  )

  def settings: Seq[ModuleID] = {
    compileDependencies
  }
}

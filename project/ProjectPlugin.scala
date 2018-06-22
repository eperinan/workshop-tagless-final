import sbt.Keys._
import sbt._

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  object autoImport {

    lazy val V = new {
      val catsCore     = "1.1.0"
      val catsEffect   = "0.10.1"
      val log4cats     = "0.0.7"
      val mockitoAll   = "1.10.19"
      val scalaCheck   = "1.14.0"
      val scalatest    = "3.0.5"
      val slf4jVersion = "1.7.25"
    }

    lazy val commonSettings: Seq[Def.Setting[_]] = Seq(
      scalacOptions := Seq(
        "-encoding",
        "UTF-8",
        "-target:jvm-1.8",
        "-unchecked",
        "-deprecation",
        "-feature",
        "-language:existentials",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-language:postfixOps",
        "-Xfuture",
        "-Yno-adapted-args",
        "-Ywarn-dead-code",
        "-Ywarn-infer-any",
        "-Xfatal-warnings",
        "-Ywarn-numeric-widen",
        "-Ywarn-value-discard",
        "-Xlint"
      ),
      libraryDependencies ++= Seq(
        "io.chrisdavenport" %% "log4cats-slf4j" % V.log4cats,
        "org.slf4j"         % "slf4j-simple"    % V.slf4jVersion,
        "org.typelevel"     %% "cats-core"      % V.catsCore,
        "org.typelevel"     %% "cats-effect"    % V.catsEffect,
        "org.scalatest"     %% "scalatest"      % V.scalatest % Test,
        "org.scalacheck"    %% "scalacheck"     % V.scalaCheck % Test,
        "org.mockito"       % "mockito-all"     % V.mockitoAll % Test
      )
    )

    lazy val appSettings: Seq[Def.Setting[_]] = Seq()
  }
}

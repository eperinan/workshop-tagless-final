import sbt.Keys._
import sbt._

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  object autoImport {

    lazy val V = new {
      val catsCore      = "1.1.0"
      val catsEffect    = "0.10.1"
      val mockitoAll    = "1.10.19"
      val scalaCheck    = "1.14.0"
      val scalatest     = "3.0.5"
    }

    lazy val commonSettings: Seq[Def.Setting[_]] = Seq(
      scalacOptions := Seq(
        "-encoding", "UTF-8",               
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
        "-Ywarn-unused-import",             
        "-Xfatal-warnings",                 
        "-Ywarn-numeric-widen",             
        "-Ywarn-value-discard",             
        "-Ywarn-unused",                    
        "-Xlint"
      ),
      libraryDependencies ++= Seq(
        "org.typelevel"               %% "cats-core"      % V.catsCore,
        "org.typelevel"               %% "cats-effect"    % V.catsEffect,
        "org.scalatest"               %% "scalatest"      % V.scalatest  % Test,
        "org.scalacheck"              %% "scalacheck"     % V.scalaCheck % Test,
        "org.mockito"                 % "mockito-all"     % V.mockitoAll % Test
      )
    )

    lazy val appSettings: Seq[Def.Setting[_]] = Seq()
  }
}

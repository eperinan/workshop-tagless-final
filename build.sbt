import sbt._

lazy val common = project
  .in(file("modules/common"))
  .settings(moduleName := "workshop-tagless-final-common")
  .settings(commonSettings)

lazy val app = project
  .in(file("modules/app"))
  .settings(moduleName := "workshop-tagless-final-app")
  .settings(appSettings)
  .dependsOn(common % "compile->compile;test->test")

lazy val `workshop-tagless-final` = project
  .in(file("."))
  .enablePlugins(ScalaUnidocPlugin)
  .aggregate(app, common)


lazy val allModules: Seq[ProjectReference] = Seq(
    common, app
)

lazy val allModulesDeps: Seq[ClasspathDependency] =
  allModules.map(
    ClasspathDependency(_, None)
  )

lazy val root = project
  .in(file("."))
  .settings(
    name := "workshop-tagless-final",
    scalaVersion := "2.12.6",
    organization := "com.eperinan.workshop.taglessfinal"
  )
  .aggregate(allModules: _*)
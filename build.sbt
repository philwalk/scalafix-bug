import sbt._
import sbt.Package.ManifestAttributes

maxErrors := 1

import Dependencies.{svdefault,sv212,sv213,svdot,sMeta}

scalaVersion := svdefault

ThisBuild / scalaVersion              := "2.13.3"
ThisBuild / name                      := "scalafix-bug"
ThisBuild / version                   := "0.0.1"
ThisBuild / organization              := "org.vastblue"
ThisBuild / asciiGraphWidth           := 1024
ThisBuild / isSnapshot                := true
ThisBuild / javaOptions ++= Seq("-XX:+UnlockCommercialFeatures", "-XX:+FlightRecorder")

lazy val supportedScalaVersions = Seq(sv213, sv212) // svdot: not yet

scalacOptions := Seq(
  "-encoding", "utf-8",
  "-feature",
  "-Xmigration",


  // Linting options
  "-unchecked",
  "-Xcheckinit",
  /*
  "-Xlint:adapted-args",
  "-Xlint:constant",
  "-Xlint:delayedinit-select",
  "-Xlint:deprecation",
  "-Xlint:doc-detached",
  "-Xlint:inaccessible",
  "-Xlint:infer-any",
  "-Xlint:missing-interpolator",
  "-Xlint:nullary-override",
  "-Xlint:nullary-unit",
  "-Xlint:option-implicit",
  "-Xlint:package-object-classes",
  "-Xlint:poly-implicit-overload",
  "-Xlint:private-shadow",
  "-Xlint:stars-align",
  "-Xlint:type-parameter-shadow",
  "-Wdead-code",
  "-Wextra-implicit",
  "-Wnumeric-widen",
  "-Wunused:implicits",
  "-Wunused:imports",
  "-Wunused:locals",
  "-Wunused:params",
  "-Wunused:patvars",
  "-Wunused:privates",
  "-Wvalue-discard",
  */
)

   scalacOptions ++= { if (isDotty.value) Seq("-language:Scala2Compat","-rewrite") else Nil }
// scalacOptions ++= { if (isDotty.value) Seq("-language:Scala2") else Nil }

scalacOptions ++= Seq("-language:implicitConversions")
//scalacOptions ++= { if (version.value.startsWith("2.12")) Seq( "-Xplugin-require:semanticdb" ) else Nil }

scalacOptions ++= (CrossVersion.partialVersion(scalaVersion.value) match {
case Some((2, n)) if n >= 13 => Seq("-Xsource:2.14")
case _ => Seq("-Yno-adapted-args")
})

//version := vast_Version // required by: sbt --info publishLocal
publishArtifact in (Compile, packageSrc) := true
publishArtifact in (Compile, packageDoc) := false

// EclipseKeys.eclipseOutput := Some("target")
//import scala.sys.process._
gitHeadCommitSha in ThisBuild := scala.sys.process.Process("git rev-parse HEAD").lineStream.head

//lazy val scalafixSemanticdbScalac = "org.scalameta" % "semanticdb-scalac" % sMeta // cross CrossVersion.full
scalafixDependencies in ThisBuild += "org.scala-lang.modules" %% "scala-collection-migrations" % "2.1.4"
libraryDependencies               +=  "org.scala-lang.modules" %% "scala-collection-compat" % "2.1.4"
scalacOptions                     ++= List("-Yrangepos", "-P:semanticdb:synthetics:on")

// /* ================================== // biz:
ThisBuild / scalafixDependencies += "org.scala-lang" %% "scala-rewrites" % "0.1.1"

scalafixDependencies in ThisBuild += "org.scalatest" %% "autofix" % "3.1.0.0" 

// addCompilerPlugin("org.scalameta" % "semanticdb-scalac" % sMeta cross CrossVersion.full)
addCompilerPlugin(scalafixSemanticdb) // enable SemanticDB

inThisBuild(
  List(
    scalaVersion := sv213,
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision, 
 // semanticdbVersion := sMeta, //  cross CrossVersion.full,
    semanticdbOptions += "-P:semanticdb:synthetics:on", // make sure to add this
    scalafixScalaBinaryVersion := CrossVersion.binaryScalaVersion(scalaVersion.value),
//  Configs.isProductionRelease := false
  )
)
// ==================================== */ // biz:

lazy val commonSettings = Seq(
//scalaVersion               := svdefault,
  organization               := "org.vastblue",
  version                    := projectVersion,
  publishArtifact in (Compile, packageDoc) := false,
  scalacOptions += "-Yresolve-term-conflict:object", // values are: "package", "object", "error"
  scalacOptions += "-deprecation",
  scalacOptions += "-feature",
  evictionWarningOptions in update := EvictionWarningOptions.default.withWarnTransitiveEvictions(false).withWarnDirectEvictions(false).withWarnScalaVersionEviction(false),
)

lazy val root = project.in(file(".")).
  settings(
    commonSettings,
    crossScalaVersions := Nil, // to avoid double-publishing
    skip in publish := true
  ).
  aggregate(vastblue)

lazy val vastblue = subProject("vastblue").
  enablePlugins(BuildInfoPlugin).
  settings(
    name                       := "vastblue",
    commonSettings,
    crossScalaVersions         := supportedScalaVersions,
       libraryDependencies ++= Dependencies.vastblueDeps.map(_.withDottyCompat(scalaVersion.value)),
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "vastblue",
  )

autoCompilerPlugins := true

// settings and tasks common to all subprojects
def subProject(name:String): Project = {
  Project(name, file(name)).
  settings(
    shellPrompt := { state => Project.extract(state).currentRef.project + "> " },
    evictionWarningOptions in update := EvictionWarningOptions.default.withWarnTransitiveEvictions(false).withWarnDirectEvictions(false).withWarnScalaVersionEviction(false),
    makeVersionProperties := {
      val propFile = (resourceManaged in Compile).value / "version.properties"
      val content = "version=%s" format (gitHeadCommitSha.value)
      IO.write(propFile, content)
      Seq(propFile)
    },
    // javacOptions does not work if you have no Java sources.
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-g", "-deprecation", "-Xlint:unchecked"),
    publishArtifact in (Compile, packageDoc) := false,
    scalacOptions ++= Seq(
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Yrangepos",// required by scalafix
      "-feature",
      "-unchecked",
      "-deprecation",
      "-Ywarn-unused",
      "-Yrangepos",
    ),

    cancelable in Global := true,
    fork in run := true, // permits ctrl-C to stop run w/out killing sbt

    parallelExecution in Test := false,

    publishArtifact in Test := false,
  
    // the following might require sbt 1.0.0 or later (not sure)
    updateOptions := updateOptions.value.withLatestSnapshots(false), // ?????
    updateOptions := updateOptions.value.withCachedResolution(true),
  
    // full stack dump for failed scalatest
    testOptions in Test += Tests.Argument("-oF"),
  )
}

lazy val ivyLocal:File = file( Path.userHome.absolutePath + "/.ivy2/local")
lazy val ivyCache:File = file( Path.userHome.absolutePath + "/.ivy2/cache")
def ivyDir = file ( Path.userHome.absolutePath + "/.ivy2" )

lazy val svSuffix = scalaVersion match {
  case str if str.toString.startsWith("2.12") => "_2.12"
  case str if str.toString.startsWith("2.13") => "_2.13"
  case other => sys.error(s"other[$other]")
}
lazy val vastblueJar:File = {
  (ivyDir ** "*.jar").filter ( f =>
    f.toString.contains(projectVersion) &&
    f.getName == s"vastblue${svSuffix}.jar"
  ).classpath.files.sortBy { _.toString }.reverse.take(1) match {
    case Seq(jar) => jar
  }
}
lazy val showVastblueJars    = taskKey[Unit]("display vastblue jar below .ivy2 dir")
lazy val gitHeadCommitSha       = taskKey[String]("Determines the current git commit SHA")
lazy val makeVersionProperties  = taskKey[Seq[File]]("Creates a version.properties file we can find at runtime.")
lazy val makeCacheFile = taskKey[Seq[File]](s"Creates dependencies cache file for use by scalav2 scripts.")

lazy val printScalaVersion    = taskKey[Unit]("display default scala version")
lazy val projectVersion  = s"0.0.1-SNAPSHOT"

lazy val showCpfiles = taskKey[Unit]("display classpath ivy files")

lazy val localPublish = taskKey[Unit]("compile and then makeCacheFile")


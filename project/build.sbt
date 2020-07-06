// vim: ft=scala
import sbt.Keys._
import sbt._

// scalaVersion := "2.10.4" // sbt compiler, not the project compiler
//scalaVersion := "2.10.6" // sbt compiler, not the project compiler
scalaVersion in ThisBuild := "2.13.3"

name         := "vastblue"

organization := "org.vastblue"

parallelExecution in Compile := true

scalacOptions := Seq(
   "-encoding"
  ,"UTF-8"
  ,"-deprecation"
  ,"-unchecked"
  ,"-explaintypes"
)

resolvers ++= Seq(
   "releases"            at "https://oss.sonatype.org/content/repositories/releases"
  ,"snapshots"           at "https://oss.sonatype.org/content/repositories/snapshots" // poi!
  ,"renci.org"           at "https://ci-dev.renci.org/nexus/content/repositories/public"
  ,"Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
)

lazy val excludedTestNames = SettingKey[Seq[String]]("excluded-tests", "temporary excluded tests")

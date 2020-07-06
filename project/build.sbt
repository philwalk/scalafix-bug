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
   "releases"                 at "https://oss.sonatype.org/content/repositories/releases"
  ,"snapshots"                at "https://oss.sonatype.org/content/repositories/snapshots" // poi!
  ,"renci.org"                at "https://ci-dev.renci.org/nexus/content/repositories/public"
  ,"Typesafe repository"      at "https://repo.typesafe.com/typesafe/releases/"
  ,"semanticdb repo hunch"    at "https://mvnrepository.com/artifact/semanticdb-scalac"
  ,"semanticdb repo"          at "https://mvnrepository.com/artifact/org.scalameta/semanticdb-scalac"
  ,"mavenrepos-sema"          at "https://mvnrepository.com/artifact/org.scalameta/semanticdb"
  ,"mavenrepository"          at "https://mvnrepository.com/artifact/org.scalameta/scalameta"
  ,"maven artifact"           at "https://mvnrepository.com/artifact"
  ,"Sonatype Releases"        at "https://oss.sonatype.org/content/repositories/releases"
  ,"Sonatype snapshots"       at "https://oss.sonatype.org/content/repositories/snapshots"
  ,"maven-central"            at "https://mvnrepository.com/artifact/org.scalameta"
  ,"Google-repo"              at "https://mvnrepository.com/artifact/com.googlecode.cobra-winldtp/ldtp"
  ,"Cobra-Ldtp-Repo"          at "https://artifacts.alfresco.com/nexus/content/repositories/public/"
  ,"maven-central-snowtide"   at "https://mvnrepository.com/artifact/com.snowtide"
  ,("snowtide-releases"       at "http://maven.snowtide.com/releases").withAllowInsecureProtocol(true)
)

lazy val excludedTestNames = SettingKey[Seq[String]]("excluded-tests", "temporary excluded tests")

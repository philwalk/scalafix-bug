import sbt._
import scalafix.sbt.ScalafixPlugin.autoImport._

object Dependencies {
  lazy val scalaOrganization = "org.scala-lang"

  // Versions
  lazy val sv211 = "2.11.12"
  lazy val sv212 = "2.12.11"
  lazy val sv213 = "2.13.3"
  lazy val svdot = "0.22.0-RC1"
  lazy val svdefault = sv213

  lazy val sMeta = "4.3.18" // "4.3.17"
  lazy val betterFilesVer = "3.9.1" // "3.8.0"

  // Libraries

  lazy val testDeps = Seq(
   "org.scalatest"                           %% "scalatest"                            % "3.2.0" % "test",
  )

  lazy val vastblueDeps = Seq(
    "org.scalameta"                           % s"semanticdb-scalac_${svdefault}"      % sMeta,
    "org.scalameta"                          %% "scalameta"                            % sMeta,
    "org.scala-lang.modules"                 %% "scala-collection-compat"              % "2.1.6",
  )

}

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

  lazy val depResolvers = Seq(
    "semanticdb repo hunch"     at "https://mvnrepository.com/artifact/semanticdb-scalac",
    "semanticdb repo"           at "https://mvnrepository.com/artifact/org.scalameta/semanticdb-scalac",
    "mavenrepos-sema"           at "https://mvnrepository.com/artifact/org.scalameta/semanticdb",
    "mavenrepository"           at "https://mvnrepository.com/artifact/org.scalameta/scalameta",
    "maven artifact"            at "https://mvnrepository.com/artifact",
    "Sonatype Releases"         at "https://oss.sonatype.org/content/repositories/releases",
    "Sonatype snapshots"        at "https://oss.sonatype.org/content/repositories/snapshots",
    "maven-central"             at "https://mvnrepository.com/artifact/org.scalameta",
    "Google-repo"               at "https://mvnrepository.com/artifact/com.googlecode.cobra-winldtp/ldtp",
    "Cobra-Ldtp-Repo"           at "https://artifacts.alfresco.com/nexus/content/repositories/public/",
    "maven-central-snowtide"    at "https://mvnrepository.com/artifact/com.snowtide",
   ("snowtide-releases"         at "http://maven.snowtide.com/releases").withAllowInsecureProtocol(true),
//   Resolver.bintrayRepo("cibotech", "public"),
//  "ScalaNLP Maven2"           at "https://repo.scalanlp.org/repo",
//  "scalagen-repo"             at "https://central.maven.org/com.mysema.scalagen/scalagen",   // NOT CORRECT, tried everything
  )
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

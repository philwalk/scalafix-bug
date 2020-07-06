scalaVersion in ThisBuild := "2.12.11"

resolvers ++= Seq(
   "typesafe repo"             at "https://repo.typesafe.com/ivy-releases",
   "semanticdb-scalac repo"    at "https://mvnrepository.com/artifact/org.scalameta/semanticdb-scalac",
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
)

  addSbtPlugin("net.virtual-void"           % "sbt-dependency-graph"         % "0.10.0-RC1")
//addSbtPlugin("ch.epfl.scala"              % "sbt-scalafix"                 % "0.9.18")
  addSbtPlugin("ch.epfl.scala"              % "sbt-scalafix"                 % "0.9.18+4-9ef73805-SNAPSHOT")
  addSbtPlugin("ch.epfl.lamp"               % "sbt-dotty"                    % "0.4.1") // "0.4.0")
  addSbtPlugin("com.eed3si9n"               % "sbt-buildinfo"                % "0.9.0")
  addSbtPlugin("ch.epfl.scala"              % "sbt-bloop"                    % "1.4.3") // "1.4.1")


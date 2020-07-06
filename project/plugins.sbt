scalaVersion in ThisBuild := "2.12.11"

/*
resolvers ++= Seq(
   "typesafe repo"             at "https://repo.typesafe.com/ivy-releases",
   "semanticdb-scalac repo"    at "https://mvnrepository.com/artifact/org.scalameta/semanticdb-scalac",
)
*/

  addSbtPlugin("net.virtual-void"           % "sbt-dependency-graph"         % "0.10.0-RC1")
//addSbtPlugin("ch.epfl.scala"              % "sbt-scalafix"                 % "0.9.18")
  addSbtPlugin("ch.epfl.scala"              % "sbt-scalafix"                 % "0.9.18+4-9ef73805-SNAPSHOT")
  addSbtPlugin("ch.epfl.lamp"               % "sbt-dotty"                    % "0.4.1") // "0.4.0")
  addSbtPlugin("com.eed3si9n"               % "sbt-buildinfo"                % "0.9.0")
  addSbtPlugin("ch.epfl.scala"              % "sbt-bloop"                    % "1.4.3") // "1.4.1")


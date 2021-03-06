ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.perseus"
ThisBuild / organizationName := "perseus"
ThisBuild / name             := "notification-service"


scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:postfixOps",
  "-Xfatal-warnings",
  "-Ypartial-unification",
  "-language:higherKinds",
  "-language:implicitConversions"
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6")
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.0-M4")
addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M11" cross CrossVersion.full)

libraryDependencies ++= {
  val catVersion = "1.5.0"
  val freeioVersion = "0.8.2"
  val doobieVersion = "0.6.0"
  val http4sVersion = "0.19.0"
  Seq(
    "io.frees"        %% "frees-core"          % freeioVersion,
    "io.frees"        %% "frees-logging"       % freeioVersion,
    "io.frees"        %% "frees-effects"       % freeioVersion,
    "io.frees"        %% "frees-cache"         % freeioVersion,
    "io.frees"        %% "frees-config"        % freeioVersion,
    "io.frees"        %% "frees-http4s"        % freeioVersion,
    "org.scalactic"   %% "scalactic"           % "3.0.5",
    "org.typelevel"   %% "cats-core"           % catVersion,
    "org.tpolecat"    %% "doobie-core"         % doobieVersion,
    "org.tpolecat"    %% "doobie-hikari"       % doobieVersion,
    "org.tpolecat"    %% "doobie-postgres"     % "0.6.0",
    "org.http4s"      %% "http4s-blaze-server" % http4sVersion,
    "org.http4s"      %% "http4s-circe"        % http4sVersion,
    "org.http4s"      %% "http4s-dsl"          % http4sVersion,
    "com.pauldijou"   %% "jwt-circe"           % "1.1.0",
    "com.olegpy"      %% "meow-mtl"            % "0.2.0"
  )
}

val circeVersion = "0.10.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-literal"
).map(_ % circeVersion)

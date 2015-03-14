enablePlugins(ScalaJSPlugin)

name := "A Star Maze Solver"

scalaVersion := "2.11.5"

scalaJSStage in Global := FastOptStage

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"
libraryDependencies += "org.scala-js" % "scalajs-tools_2.11" % "0.6.0"
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")
import sbt._
import Keys._
import sbtassembly.AssemblyPlugin.autoImport._

object HiveUdfBuild extends Build {

  lazy val commonSettings = Seq(
    name := "hive-udf",
    version := "1.0",
    scalaVersion := "2.11.2",
    organization := "org.lobocki",
    resolvers ++= Seq(
      "Concurrent Maven Repo" at "http://conjars.org/repo",
      "maven" at "http://repo2.maven.org/maven"),
    libraryDependencies ++= Seq(
      "org.apache.hive" % "hive-exec" % "0.14.0" % "provided",
      "org.apache.hadoop" % "hadoop-common" % "2.6.0" % "provided",
      "org.apache.calcite" % "calcite-avatica" % "0.9.2-incubating" % "provided",
      "org.apache.calcite" % "calcite-core" % "0.9.2-incubating" % "provided",
      "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"),
    assemblyJarName in assembly := name.value + "-" + version.value + ".jar",
    assemblyMergeStrategy in assembly := {
      case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".xml" => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".txt" => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
      case "application.conf" => MergeStrategy.concat
      case "unwanted.txt" => MergeStrategy.discard
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    })

  lazy val root = (project in file(".")).
    settings(commonSettings: _*)

}
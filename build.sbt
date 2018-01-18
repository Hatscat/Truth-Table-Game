scalaVersion := "2.11.8"

enablePlugins(AndroidApp)
android.useSupportVectors

versionCode := Some(1)
version := "0.1-SNAPSHOT"

instrumentTestRunner :=
  "android.support.test.runner.AndroidJUnitRunner"

javacOptions in Compile ++= "-source" :: "1.7" :: "-target" :: "1.7" :: Nil

libraryDependencies ++=
  "com.android.support" % "appcompat-v7" % "24.0.0" ::
  "com.android.support.test" % "runner" % "0.5" % "androidTest" ::
  "com.android.support.test.espresso" % "espresso-core" % "2.2.2" % "androidTest" ::
//  "org.scalactic" %% "scalactic" % "3.0.4" ::
//  "org.scalatest" %% "scalatest" % "3.0.4" % "test" ::
  Nil

//  "org.processing" % "core" % "3.3.6" ::
//libraryDependencies += "org.processing" % "core" % "3.3.6"
libraryDependencies += "org.processing" % "android-core" % "4.0" from "file:///C:/Users/lubou/processing-AndroidMode/processing-android-core.jar"

//
//libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
//
//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"


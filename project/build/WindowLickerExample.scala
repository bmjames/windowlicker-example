import sbt._

class WindowLickerExample(info: ProjectInfo) extends DefaultProject(info) {
  val scalatest = "org.scalatest" % "scalatest" % "1.2"
  val jUnit = "junit" % "junit" % "4.8.1"
}
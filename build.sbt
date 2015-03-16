name := "PlayWarehouse"

version := "1.0"

lazy val `playwarehouse` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc , anorm , cache , ws,
  "net.sf.barcode4j" % "barcode4j" % "2.0",
  "mysql" % "mysql-connector-java" % "5.1.34"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
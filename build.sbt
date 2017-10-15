name := "PlayWarehouse"

version := "1.0"

lazy val `playwarehouse` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  guice,
  "net.sf.barcode4j"  % "barcode4j"              % "2.0",
  "com.h2database"    % "h2"                     % "1.4.192"
)

//unmanagedResourceDirectories in Test +=  baseDirectory ( _ /"target/web/public/test" )

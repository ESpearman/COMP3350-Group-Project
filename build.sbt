name := "COMP3350-Group-Project"

version := "INDEV"

scalaVersion := "2.11.0"

resolvers += "swt-repo" at "https://swt-repo.googlecode.com/svn/repo/"

lazy val os = (sys.props("os.name"), sys.props("os.arch")) match {
  case ("Linux", _) => "gtk.linux.x86"
  //case ("Mac OS X", "amd64" | "x86_64") => "cocoa.macosx.x86_64"
  case ("Mac OS X", _) => "cocoa.macosx.x86_64"
  //case (os, "amd64") if os.startsWith("Windows") => "win32.win32.x86_64"
  case (theOS, _) if theOS.startsWith("Windows") => "win32.win32.x86_64"
  case (theOS, arch) => sys.error("Cannot obtain lib for OS '" + theOS + "' and architecture '" + arch + "'")
}
lazy val swt_artifact = "org.eclipse.swt." + os

libraryDependencies ++= Seq(
  "org.eclipse.swt" % swt_artifact % "3.8" withSources(),
  "junit" % "junit" % "4.10" withSources(),
  "org.projectlombok" %% "lombok-maven" % "1.12.6.0" withSources(),
  "com.typesafe" % "config" % "1.2.1" withSources(),
  "com.typesafe.slick" %% "slick" % "2.0.2" withSources(),
  "org.slf4j" % "slf4j-nop" % "1.6.4" withSources()
)
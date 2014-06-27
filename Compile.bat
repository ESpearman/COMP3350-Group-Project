cd bin
erase /S /Q *.class
cd ..

set CLASSPATH=.;bin\;lib\*;src\test\testFiles\*


javac -d bin\ -cp %classpath% src\lms\application\*.java src\lms\business\*.java src\lms\business\logic\*.java src\lms\persistence\*.java src\lms\config\*.java src\lms\reset\*.java

javac -d bin\ -cp %classpath% src\test\business\logic\*.java
javac -d bin\ -cp %classpath% src\test\stubdb\*.java
javac -d bin\ -cp %classpath% src\test\integration\hsqldb\*.java
javac -d bin\ -cp %classpath% src\test\*.java

pause
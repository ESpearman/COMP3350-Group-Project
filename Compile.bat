cd bin
erase /S /Q *.class
cd ..

set CLASSPATH=.;bin\;lib\*;src\test\testFiles\*


javac -d bin\ -cp %classpath% src\lms\application\*.java src\lms\domainobjects\*.java src\lms\businesslogic\*.java src\lms\persistence\*.java src\lms\config\*.java src\lms\reset\*.java

javac -d bin\ -cp %classpath% src\test\businesslogic\*.java
javac -d bin\ -cp %classpath% src\test\persistence\*.java
javac -d bin\ -cp %classpath% src\test\*.java

pause
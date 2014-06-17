COMP3350-Group-Project
======================

Version control system location :
https://github.com/ESpearman/COMP3350-Group-Project

When our JUnit tests were run in eclipse during development, all of them ran and passed, and continue to do so, however, when we tried running them via our RunUnitTest.bat script, several of our tests began failing while still passing when run through eclipse. We discovered this within an hour of the submission deadline and we were unable to resolve the issue in time.

Importing
	An excel file of student or locker information can be imported opening the import window (click import button), typing in the absolute path the file and clicking the button to select which type of information to import. Some example Excel files are located in src/test/testFiles/.
	
Student Registration
	Click the register button from the main menu will bring up the student reigstration window. This window contains a search feature that allows lookup of student information for students already in the database, and allows new students to be registered. The register button registers a student in the database and takes you to a new window allowing the assignment of lockers.

src
    lms.application
        ImportWindow.java
        LockerWindow.java
        MainWindow.java
        RegisterWindow.java
    lms.business
        Building.java
        BusinessObject.java
        Locker.java
        LockerSize.java
        Rental.java
        Student.java
        Term.java
        TermBased.java
    lms.business.logic
        LockerPrice.java
        SearchStudents.java
        SpreadsheetImporter.java
		RegisterStudent.java
    lms.db
        DBProxy.java
    lms.stubdb
        StubDB.java
	test
		AllTests.java
    test.business.logic
        SearchStudentTest.java
        SpreadsheetImporterTest.java
		RegisterStudentsTest.java
		SearchLockersTest.java
	test.stubdb
		StubDBTest.java

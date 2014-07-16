COMP3350-Group-Project
======================
Presentation Doc Link:
https://docs.google.com/presentation/d/155N_fykk91gH2sMwz0tzDvL2ZqYcR3Yei6G4jFNkItE/edit#slide=id.p

Version control system location :
https://github.com/ESpearman/COMP3350-Group-Project
Iteration 3 files can be found in the iteration 3 branch

When running ATs the resetDB script must be run before each run of the RunAts script. We meant to combine these, however, a timing issue with the database lock prevented us from doing so

We were unable to create automated acceptance tests for 4 of our big user stories. 3 of these were due to the fact that they were regarding the exporting of data to a file, and running them forces the opening of the browse file dialog, which is not compatible with ATR. The 4th one is due to the fact that it requires editing a config file, which ATR cannot do. We have provided only a manual test for these big user stories. The other big user stories all have both an automated test and a manual test.

The ResetDB script should be run before each time a manual acceptance test is run

Regarding integration tests for each seam: We were told in class that our business logic should be tested against the persistance layer in unit tests using the stub DB. Because these tests have good covereage of this seam, we did not create any explicit integration tests for this seam. There are however integration tests for importing excel spreadsheets, as well as between the persistance layer and the actual database. We do not have integration tests between our domain objects and other layers due to the fact that this integration is covered by the unit tests of the other layers due to high coupling between domain objects and the other layers.

The only methods in our domain objects that can be tested using unit tests are the parsers. However, we were unable to create unit tests for them due to the fact that the objects we are parsing from must be created by the HSQLDB driver. Thus, there are no unit tests for domain objects.

- When our JUnit tests were run in eclipse during development, all of them ran and passed, and continue to do so,
  however, when we tried running them via our RunUnitTest.bat script, several of our tests began failing
  while still passing when run through eclipse. This is a well documented issue with the way the dom4j library handles class loading.
  The tests run fine if the "Reload classes every run" checkbox is left unchecked.

- The Assignment 2 specification document only mentioned having a RunUnitTest.bat file, but nothing about one to run the integration tests.
  We've added RunIntegrationTest.bat for this purpose.

======================
How to use
======================

Importing data
	To import data, first go to the setup menu and click "Import". Importing takes an excel document and imports either the
	student or locker information within it (spreadsheets pre-formatted with data are in the project's source directory
	in the "spreadsheets" folder. After choosing a file select the appropriate radio button and click import. The student and
	locker information will now be in the database.
	
Adding buildings
	To add a new building to the database, from the Setup menu simply click "Add Building", type in a name for the building to
	be created, and click "Add". The building information will now be in the database.

Adding lockers
	In instances where locker informations may not be up to date with the spreadsheet, manual allocation of locker information
	is possible. To add a locker, from the Setup menu click "Add locker". Choose an existing building to add to, then type in 
	the locker's number and click "Add". An issue right now is that the number field accepts non-numeric input, and accepts duplicate
	locker entries per building, but this will be fixed in the next iteration.

Editing student information
	If there's discrepancy between the actual information on a student and what was imported on the spreadsheet, the user can
	manually edit that information with the Setup menu "Edit student" feature. Simply search for a student by their student number,
	edit the information, and click "Edit".
	
Exporting documents
	In the main window's Export menu, the user can export information about the system to external documents. As of this iteration,
	only student email exporting has been added, but later iterations will have functionality for a list of student rentals and a list
	lockers. To export emails, go to Export, click the email radio button, click "Export" and from the file dialog navigate to where the
	file should be saved. Click save and the file created will have a semicolon separated list of the email addresses of students currently
	renting a locker.
	
Registering students
	Clicking the register button from the main menu will bring up the student registration window.
	This window contains a search feature that allows lookup of student information for students already
	in the database, and allows new students to be registered. The register button registers a student
	in the database and takes you to a new window allowing the assignment of lockers.
	
Assigning lockers
	Once the student has been selected, choose a building and a list of available lockers will be populated
	in the locker dropdown menu. Once a locker has been selected, the price will be calculated based on the locker's
	size and the student's faculty. Click rent, and the if the student is not currently renting a locker, the transaction
	will succeed.

=======================
Recent Changes
=======================
- Config file added for DB parameters and locker prices
- SearchStudent and SearchLocker classes replaced by DB queries
- Added features for editing student info, adding lockers and students, and exporting emails
- Added HSQLDB database
- Both HSQLDB implement and StubDB implement a common interface
- Added dependency injection for databases
- Revamped package structure

=======================
Packages
=======================
src
    lms.application
    	AboutWindow.java
        AddBuildingWindow.java
        AddLockerWindow.java
        AddTerm.java
        ExportWindow.java
        ImportWindow.java
        LockerWindow.java
        MainWindow.java
        PopupWindow.java
        SetupWindow.java
        StudentWindow.java
    lms.businesslocgic
        AddBuilding.java
        AddLocker.java
        AddTerm.java
        CurrentTermInfo.java
        DemoDataGenerator.java
        EmailExport.java
        GetStats.java
        LockerPrice.java
        RegisterStudent.java
        RentLocker.java
        SpreadsheetImporter.java
        SpreadsheetExporter.java
    lms.config
    	ConfigData.java
    lms.domainobjects
        Building.java
        BusinessObject.java
        Locker.java
        LockerSize.java
        Rental.java
        Student.java
        Term.java
        TermBased.java
    lms.persistence
        ConnectionPool.java
        DBInjectable.java
        DBInjector.java
        DBProxy.java
        HSQLDBImpl.java
        IDB.java
        StubDBImpl.java
    lms.reset
        DBReset.java
    test
	IntegrationTests.java
	UnitTests.java
    test.business.logic
        AddBuildingTest.java
        AddLockerTest.java
        AddTermTest.java
        EmailExportTest.java
        LockerTest.java
        RegisterStudentsTest.java
        RentLockerTest.java
        SpreadsheetExporterTest.java
        SpreadsheetImporterTest.java
     test.persistence
	HSQLDBImplTest.java
	StubDBImplTest.java

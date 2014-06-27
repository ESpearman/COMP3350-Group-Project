package test;

import test.business.logic.AddBuildingTest;
import test.business.logic.AddLockerTest;
import test.business.logic.EmailExportTest;
import test.business.logic.RegisterStudentsTest;
import test.business.logic.RentLockerTest;
import test.integration.excel.SpreadsheetImporterImplTest;
import test.integration.hsqldb.HSQLDBImplTest;
import test.stubdb.StubDBTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testLogic();
        testStubDB();
        testIntegration();
        return suite;
    }

    private static void testLogic()
    {
    	suite.addTestSuite(AddBuildingTest.class);
    	suite.addTestSuite(AddLockerTest.class);
    	suite.addTestSuite(EmailExportTest.class);
        suite.addTestSuite(RegisterStudentsTest.class);
        suite.addTestSuite(RentLockerTest.class);
    }

    private static void testStubDB()
    {
        suite.addTestSuite(StubDBTest.class);
    }
    
    private static void testIntegration()
    {
    	suite.addTestSuite(HSQLDBImplTest.class);
    	suite.addTestSuite(SpreadsheetImporterImplTest.class);
    }
}

package test;

import test.business.logic.RegisterStudentsTest;
import test.business.logic.RentLockerTest;
import test.business.logic.SpreadsheetImporterTest;
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
        integration();
        return suite;
    }

    private static void testLogic()
    {
        suite.addTestSuite(RegisterStudentsTest.class);
        suite.addTestSuite(RentLockerTest.class);
        suite.addTestSuite(SpreadsheetImporterTest.class);
    }

    private static void testStubDB()
    {
        suite.addTestSuite(StubDBTest.class);
    }
    
    private static void integration()
    {
    	suite.addTestSuite(HSQLDBImplTest.class);
    }
}

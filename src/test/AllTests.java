package test;

import test.businesslogic.RegisterStudentsTest;
import test.businesslogic.RentLockerTest;
import test.integration.excel.SpreadsheetImporterImplTest;
import test.persistence.HSQLDBImplTest;
import test.persistence.StubDBImplTest;
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
    }

    private static void testStubDB()
    {
        suite.addTestSuite(StubDBImplTest.class);
    }
    
    private static void integration()
    {
    	suite.addTestSuite(HSQLDBImplTest.class);
    	suite.addTestSuite(SpreadsheetImporterTest.class);
    }
}

package test;

import test.business.logic.RegisterStudentsTest;
import test.business.logic.SearchStudentTest;
import test.business.logic.SearchLockersTest;
import test.business.logic.SpreadsheetImporterTest;
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
        return suite;
    }

    private static void testLogic()
    {
        suite.addTestSuite(RegisterStudentsTest.class);
        suite.addTestSuite(SearchStudentTest.class);
        suite.addTestSuite(SearchLockersTest.class);
        suite.addTestSuite(SpreadsheetImporterTest.class);
    }

    private static void testStubDB()
    {
        suite.addTestSuite(StubDBTest.class);
    }
}

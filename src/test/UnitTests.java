package test;

import test.businesslogic.*;
import test.persistence.*;

import junit.framework.Test;
import junit.framework.TestSuite;

public class UnitTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Unit tests");
        testLogic();
        testStubDB();
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
}

package test;

import test.businesslogic.*;
import test.persistence.*;

import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        integration();
        return suite;
    }
    
    private static void integration()
    {
    	suite.addTestSuite(HSQLDBImplTest.class);
    	suite.addTestSuite(SpreadsheetImporterTest.class);
    }
}

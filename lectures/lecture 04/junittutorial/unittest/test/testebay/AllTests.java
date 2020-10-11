package testebay;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Sample test suite includes all the test cases in package test/testebay
 * 
 * @author Bin Han
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for testebay");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestProduct.class);
		suite.addTestSuite(TestShoppingCart.class);
		//$JUnit-END$
		return suite;
	}

}

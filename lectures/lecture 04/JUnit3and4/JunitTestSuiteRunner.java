//JUnit3 test suite
import junit.framework.*;
//class that a test case runner uses to automatically run test cases
public class JunitTestSuiteRunner {
//needs to implement the suite() method that the test case runner uses
public static Test suite(){
	  // add the tests in the suite
      TestSuite suite = new TestSuite();
      //test classes need to extend TestCase
      //only JUnit3 test cases can be used here
      suite.addTestSuite(TestJunit7.class);
      suite.addTestSuite(TestJunit2.class);
      return suite;
    }
}
//JUnit 4 test class
//Tests many assertions in separate test cases
//With testing context as data members of the testing class
//Where all data members are initialized by an @Before method
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class TestJunit5 {

   String str1, str2, str3, str4, str5;
   
   @Before public void beforeEachTest(){
	   str1 = new String ("abc");
	   str2 = new String ("abc");
	   str3 = null;
	   str4 = "abc";
	   str5 = "abc";
   }
   @Test public void testAssertions1() {
      assertEquals(str1, str2);}

   @Test public void testAssertions2() {
      assertNotNull(str1);}

   @Test public void testAssertions3() {
      assertNull(str3);}

   @Test public void testAssertions4() {
      assertSame(str4,str5);}

   @Test public void testAssertions5() {
      assertNotSame(str1,str3);}
}

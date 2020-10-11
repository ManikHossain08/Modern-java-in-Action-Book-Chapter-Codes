//JUnit 3 test class
//Tests many assertions, each with a different test case
//With testing context as data members of the testing class
//Where all data members are initialized upon declaration
import junit.framework.TestCase;

public class TestJunit7 extends TestCase{

   String str1 = new String ("abc");
   String str2 = new String ("abc");
   String str3 = null;
   String str4 = "abc";
   String str5 = "abc";
    
   public void testAssertions1() {
      assertEquals(str1, str2);}

   public void testAssertions4() {
      assertNotNull(str1);}

   public void testAssertions5() {
      assertNull(str3);}

   public void testAssertions6() {
      assertSame(str4,str5);}

   public void testAssertions7() {
      assertNotSame(str1,str3);}
}

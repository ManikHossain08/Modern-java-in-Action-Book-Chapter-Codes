//JUnit 3 test class
import junit.framework.TestCase;

public class TestJunit2 extends TestCase{

   String message;	
   MessageUtil messageUtil;
 
   public void setUp(){
	  System.out.println("inside setUp() ");
	  message = "Robert";	
	  messageUtil = new MessageUtil(message);
   }
   public void tearDown(){
	  System.out.println("inside tearDown() ");
   }
   public void testSalutationMessage() {
      System.out.println("Inside testSalutationMessage() ");
      message = "Hi!" + "Robert";
      assertEquals(message,messageUtil.salutationMessage());
   }
   public void testPrintMessage() {	
	  System.out.println("Inside testPrintMessage()");    
	  assertEquals(message, messageUtil.printMessage());     
   }
}
//inside setUp()
//Inside testSalutationMessage()
//Hi!Robert
//inside tearDown()
//inside setUp()
//Inside testPrintMessage()
//Robert
//inside tearDown()
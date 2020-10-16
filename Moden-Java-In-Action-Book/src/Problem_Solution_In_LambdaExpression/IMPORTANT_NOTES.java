package Problem_Solution_In_LambdaExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class IMPORTANT_NOTES {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		
		
		//----- **** Special void-compatibility rule (TARGET TYPING) *****-----//
		
		// Predicate has a boolean return
		Predicate<String> p = s -> list.add(s);
		
		// Consumer has a void return
		Consumer<String> b = s -> list.add(s);
		
		
		// So before creating any boolean or void type interface please remember and
		// use these interface for lambda expression.
		
		// ****** Effectively Final *****//
		// http://ilkinulas.github.io/programming/java/2016/03/27/effectively-final-java.html
		// effectively final variables. A variable which is not declared as final but whose value is never changed 
		// after initialization is effectively final. 
		
		int portNumber = 1337;
		Runnable r = () -> System.out.println(portNumber);
		r.run();
		//portNumber = 31337; 
		
		// IF WE UNCOMMENT THIS LINE THIS WILL SHOW ERROR BECAUSE THE VALUE WILL BE CHANGED.
		// If the value of the captured variable (USED IN THE LAMBDA EXPRESSION) 
		// changes the compiler gives the same error as the 
		
		
		// when you are using Thread() interface you have to use Runnable instance as parameter of the 
		// thread constructor. SEE EXAMPLE : Thread_Runnable_Example CLASS INSIDE PACKAGE PracticeLambdaExpression
		Thread t = new Thread(() -> System.out.println("Hello World printing by run() method"));
		t.start();
		t.run();
		
		
		for (int i = 0; i < 10; i++) {
		    int counter = i;
		    new Thread(() -> {
		        System.out.println("i = " + counter);
		    }).start();
		}
		
		
	}

}

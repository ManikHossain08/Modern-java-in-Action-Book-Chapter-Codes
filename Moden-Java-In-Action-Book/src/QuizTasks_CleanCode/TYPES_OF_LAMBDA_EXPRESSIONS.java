package QuizTasks_CleanCode;

import java.util.function.Supplier;

import chapter01.Apple;

/*
	Function Descriptors:
	The abstract method in the functional interface is called a function descriptor.
	The signature of the abstract method describes the signature of the lambda expression.
 */

public class TYPES_OF_LAMBDA_EXPRESSIONS {

	public static void main(String[] args) {

		System.out.println(createApple(() -> new Apple(10, "Red")));
		
		process(() -> System.out.println("Hellow World"));
		
		// Constructor references by supplier functions
		Supplier<Apple> c1 = () -> new Apple(20, "BLUE");
		Apple a1 = c1.get();
		System.out.println(a1);
		
		// print value by consumer functions
	}

	public static Apple createApple(iNewObject o) {

		return o.getNewApple();
	}

	public static void process(Runnable r) {
		r.run();
	}

}

interface iNewObject {
	public Apple getNewApple(); // this abstract function is called functional descriptor
}




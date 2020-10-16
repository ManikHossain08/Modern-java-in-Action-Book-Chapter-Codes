package Problem_Solution_In_LambdaExpression;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Important Links:
 * 		// https://javarevisited.blogspot.com/2014/02/10-example-of-lambda-expressions-in-java8.html
 * 		// https://objectcomputing.com/resources/publications/sett/february-2013-java-8-project-lambda
 * 		// http://tutorials.jenkov.com/java/lambda-expressions.html
 * 		// https://www.geeksforgeeks.org/lambda-expressions-java-8/
 * 		// https://www.baeldung.com/java-8-lambda-expressions-tips
 * 		// https://www.javacodegeeks.com/2016/01/java-8-lambda-expression-design-patterns-strategy-design-pattern.html
 * 		// https://www.programiz.com/java-programming/lambda-expression
 * 		// 
 * 
 */





import chapter01.Apple;

/*
	Function Descriptors:
	The abstract method in the functional interface is called a function descriptor.
	The signature of the abstract method describes the signature of the lambda expression.
	
	Java 8+ Lambdas
	• A lambda expression can be understood as a kind of anonymous function: it
	doesn’t have a name, but it has a list of parameters, a body, a return type, and
	also possibly a list of exceptions that can be thrown.
	• Lambda expressions let you pass code concisely
	• A functional interface is an interface that declares exactly one abstract method
	• Lambda expressions can be used only where a functional interface is expected
	• Lambda expressions let you provide the implementation of the abstract method
	of a functional interface directly inline and treat the whole expression as an
	instance of a functional interface
	
	
	Java 8 Lambdas (II)
	• Java 8 comes with a list of common functional interfaces in the
	java.util.function package, which includes Predicate<T>, Function<T,R>, Supplier<T>, Consumer<T>, and BinaryOperator<T>
	• There are primitive specializations of common generic functional interfaces
	such as Predicate<T> and Function<T, R> that can be used to avoid
	boxing operations: IntPredicate, IntToLongFunction, and so on
	• The execute around pattern can be used with lambdas to gain additional
	flexibility and reusability
	• The type expected for a lambda expression is called the target type
	• Method references let you reuse an existing method implementation and pass it
	around directly
	• Functional interfaces such as Comparator, Predicate, and Function
	have several default methods that can be used to combine lambda expressions.
	
 */

//note: print value by consumer functions

public class TYPES_OF_LAMBDA_EXPRESSIONS_And_LectureNote {

	public static void main(String[] args) {

		System.out.println(createApple(() -> new Apple(10, "Red")));

		process(() -> System.out.println("Hellow World"));

		System.out.println("\n// ---------- Constructor references by supplier functions and lambda expression --------//");
		// Constructor references by supplier functions and lambda expression
		Supplier<Apple> c1 = () -> new Apple(20, "BLUE");
		Apple a1 = c1.get();
		System.out.println(a1);

		// Now with constructor reference
		Supplier<Apple> c2 = Apple::new;
		Apple a2 = c2.get();
		System.out.println(a2);

		System.out.println("\n// ---------- Sorting --------//");

		List<Apple> apples = Arrays.asList(new Apple(100, "Red"), new Apple(150, "green"), new Apple(40, "Red"),
				new Apple(140, "Blue"), new Apple(400, "white"));
		apples.sort(comparing(Apple::getWeight)); // compare Using method references
		apples.sort(comparing(a -> a.getWeight())); // More readable with comparing helper method

		Collections.sort(apples, (a, b) -> a.getWeight() - b.getWeight());
		apples.sort(new AppleComparator());

		System.out.println(apples);

		apples.sort(comparing(Apple::getWeight).reversed()); // compare and reversed using method references
		System.out.println(apples);

		apples.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor)); // compare and reversed
																							// using method references
		System.out.println(apples);

		System.out.println("\n// ---------- Composing Predicates --------//");
		/*
		 * Predicate interface: Additional methods: negate, and, or
		 */

		// Example: Apples that are red and heavy or green
		// explanation: if the apple is 'red' then it must be over '50g' OR add if any
		// apple is 'green'.

		Predicate<Apple> redApple = (Apple a) -> a.getColor() == "Red";

		Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 50)
				.or(a -> "green".equals(a.getColor()));

		// apples.addAll(FilteringApples_ch01.inventory);

		List<Apple> results = filterApples(apples, redAndHeavyAppleOrGreen);
		System.out.println(results);

		// exclude all the red apples
		Predicate<Apple> notRedApple = redApple.negate();
		results = filterApples(apples, notRedApple);
		System.out.println(results);

		/*
		 * Math: function composition Let f (x) = x + 1 and g(x) = x  2, then we can
		 * compose both functions, written g(f (x)) or (g o f )(x).
		 */

		// Java 8: andThen
		// To implement (g  f )(x), use andThen
		Function<Integer, Integer> f = x -> x + 1;
		Function<Integer, Integer> g = x -> x * 2;
		Function<Integer, Integer> AndThen = f.andThen(g); // f execute first then g [ total = 4, by input value = 1]
		int g_f_x = AndThen.apply(1);
		System.out.println("andThen :" + g_f_x);

		// Java 8: compose
		// To implement f(g (x)), use compose
		Function<Integer, Integer> compose = f.compose(g); // g execute first then f [ total = 3, , by input value = 1]
		int f_g_x = compose.apply(1);
		System.out.println("compose :" + f_g_x);

		System.out.println("\n//---------Example: Transformation Pipelines--------");
		// Using function composition
		
		// creating instance variable, alternatively we can also use static method and could call those method 
		// by class name but i did not do that because class name was long thats why.
		TYPES_OF_LAMBDA_EXPRESSIONS_And_LectureNote classObj = new TYPES_OF_LAMBDA_EXPRESSIONS_And_LectureNote();
		
		
		Function<String, String> addHeader = classObj::addHeader;
		Function<String, String> transformationPipeline = addHeader.andThen(classObj::checkSpelling)
																   .andThen(classObj::addFooter);
		// here everything is happening by composition because output of one function then its become to the 
		// input of next functions like previous composition. see page 50 of sildes08.
		
		System.out.println(transformationPipeline.apply("labda expression"));
		

	}

	public static Apple createApple(iNewObject o) {

		return o.getNewApple();
	}

	public static void process(Runnable r) {
		r.run();
	}

	public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	public String addHeader(String text) {
		return "From Raoul, Mario and Alan: " + text;
	}

	public String addFooter(String text) {
		return text + " Kind regards";
	}

	public String checkSpelling(String text) {
		return text.replaceAll("labda", "lambda");
	}

}

interface iNewObject {
	public Apple getNewApple(); // this abstract function is called functional descriptor
}

class AppleComparator implements Comparator<Apple> {
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight() - a2.getWeight();
	}
}

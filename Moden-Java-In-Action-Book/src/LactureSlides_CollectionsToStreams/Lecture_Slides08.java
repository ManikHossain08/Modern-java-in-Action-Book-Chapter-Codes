package LactureSlides_CollectionsToStreams;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import chapter01.Apple;



public class Lecture_Slides08 {

	/*
	 * Rules:
		Lambdas can capture:
		• instance variables
		• static variables
		• final local variables
		• effectively final local variables
		
		Closures in Java 8?
		• Java 8+ does not allow lambdas to modify variables defined outside its scope
		• However, they can be passed as method arguments
		• and read variables outside their scope
		
	 */
	
	
	
	public static void main(String[] args) {
		// -------- Consumer (with Lambda Expression) ---------//

		forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
		System.out.println();

		List<Integer> list = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
		list.forEach(System.out::println);

		// ---------- Primitive Specializations ------------//
		IntPredicate evenNumbers = (int i) -> i % 2 == 0;
		evenNumbers.test(1000);
		
		// ----  Method References ------- Putting lambdas and method references into practice -------//
		List<Apple> inventory = Arrays.asList(new Apple(80, "GREEN"), new Apple(155, "GREEN"),
				new Apple(120, "RED"));
		inventory.sort(comparing(Apple::getWeight).reversed()); // sorting against value 
		inventory.forEach(System.out::println);
		System.out.println();
		
		inventory.sort(comparing(Apple::getWeight)
				.reversed()
				.thenComparing(Apple::getColor)); // Chaining Comparators
		inventory.forEach(System.out::println);
		System.out.println();
		
		// ------ Composing Predicates ---------//
		// Predicate interface
		// Additional methods: negate, and, or
		// Example: Apples that are not red
		
//			Predicate<Apple> notRedApple = redApple.negate();
//			Example: Apples that are red and heavy
//			Predicate<Apple> redAndHeavyApple
//			= redApple.and(a -> a.getWeight() > 150);
//			Example: Apples that are red and heavy or green
//			Predicate<Apple> redAndHeavyAppleOrGreen =
//			redApple.and(a -> a.getWeight() > 150)
//			.or(a -> "green".equals(a.getColor()));
		
		
		
		// filter using method reference and predicate interface
		filter(inventory, Apple::isGreen).forEach(System.out::println); // printed by consumer interface which return void
		
		//Now with constructor reference
		Supplier<Apple> c1 = Apple::new;
		Apple a1 = c1.get();
		System.out.println();
		System.out.println(a1);
		System.out.println();
		
		// Now with constructor reference
		Function<Integer, Apple> c2 = Apple::new;
		Apple a2 = c2.apply(110);
		System.out.println(a2);
		System.out.println();

	}

	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T i : list) {
			c.accept(i);
		}
	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T s : list) {
			result.add(f.apply(s));
		}
		return result;
	}
	
	public static List<Apple> filter(List<Apple> inventory, Predicate<Apple> p) {
	    List<Apple> result = new ArrayList<>();
	    for (Apple apple : inventory) {
	      if (p.test(apple)) {
	        result.add(apple);
	      }
	    }
	    return result;
	  }
	
}

@FunctionalInterface
interface Consumer<T> {
	void accept(T t);
}

@FunctionalInterface
interface Function<T, R> {
	R apply(T t); // any type of input and any type of output is accepted
}

@FunctionalInterface
interface IntPredicate {
	boolean test(int t);
}

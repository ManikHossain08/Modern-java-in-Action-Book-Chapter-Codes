package PracticeLambdaExpression;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

// GO THROUGH ALL THE interfaces for implementing the builtin interface
// instead of creating new interface: https://docs.oracle.com/javase/8/docs/api/

// tutorials: https://www.youtube.com/watch?v=SEGEbGoH4LI&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3&index=17

public class LambdaExpressionAnimal {
	public static List<Animal> animals = Arrays.asList(new Animal(10, "Doggy", "Red"), new Animal(20, "Commy", "Red"),
			new Animal(30, "Savvi", "Blue"), new Animal(40, "Mummy", "Black"));
	public static void main(String[] args) {
		
		
		// solution -1 , sorting animal
		Collections.sort(animals, new Comparator<Animal>() {

			@Override
			public int compare(Animal o1, Animal o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		// solution -2, sorting animal
		Collections.sort(animals, (a, b) -> a.getName().compareTo(b.getName()));

		// print animal conditionally
		printAllAnimalConditionally(animals, a -> true);
		System.out.println();

		// print animal conditionally
		printAllAnimalConditionally(animals, a -> a.getName().startsWith("C"));
		System.out.println();
		
		// Solution-3, print animal conditionally
		printAllAnimalConditionally(animals, new iAnimal() {
			@Override
			public boolean test(Animal a) {
				return a.getName().endsWith("y");
			}
		});
		System.out.println();
		
		// print animal conditionally
		printAllByPredicateInterface(animals, a -> a.getName().startsWith("C"));
		System.out.println();
		
		// print animal conditionally
		printAllByConsumerInterface(animals, a -> a.getName().startsWith("C"), a -> System.out.println(a.getColor()));
		System.out.println();
		

	}
	// way-1, custom interface by developing new one
	private static void printAllAnimalConditionally(List<Animal> animals, iAnimal condition) {
		for (Animal animal : animals) {
			if (condition.test(animal))
				System.out.println(animal);
		}
	}
	
	// way-02, this is more accurate without creating new interface
	private static void printAllByPredicateInterface(List<Animal> animals, Predicate<Animal> predicate) {
		for (Animal animal : animals) {
			if (predicate.test(animal))
				System.out.println(animal);
		}
	}
	
	//way-03 this is more accurate without creating interface for printing differently
	private static void printAllByConsumerInterface(List<Animal> animals, Predicate<Animal> predicate, Consumer<Animal> consuper) {
			for (Animal animal : animals) {
				if (predicate.test(animal))
					consuper.accept(animal);
			}
		}
	
}

// this interface is same as the predicate interface that already developed by java.util
// hence, when we need this kind of interface we can actually use predicate<Animal> interface.
@FunctionalInterface
interface iAnimal {
	boolean test(Animal a);
}
package Chaptered_PracticeLambdaExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaWithGeneric {

	public static void main(String[] args) {
		List<Animal> animals = LambdaExpressionAnimal.animals;
		filterObjects(animals, a -> a.getAge() > 10, a -> System.out.println(a));
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		
		filterObjects(numbers, i -> i%2 == 1, i -> System.out.println(i));
		
	}

	public static <T> List<T> filterObjects(List<T> list, Predicate<T> p, Consumer<T> consumer) {
		List<T> newList = new ArrayList<>();

		for (T t : list) {
			if (p.test(t))
				newList.add(t);
				consumer.accept(t);
		}

		return list;
	}

}

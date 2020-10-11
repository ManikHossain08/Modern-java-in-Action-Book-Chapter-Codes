package Chapter03_Solutions;

import chapter01.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LamdaConstruction {
	public static void main(String... args) {

		List<Apple> inventory = new ArrayList<>();
		String[] colors = { "red", "green", "blue" };

		// Creating an inventory of apple with decreasing order of weight
		for (int i = 9; i >= 1; i--) {
			Apple apple = new Apple(i, colors[i % 3]);
			inventory.add(apple);
		}

		// Before Java 8
		Comparator<Apple> byWeight = new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return Integer.compare(o1.getWeight(), o2.getWeight());
			}
		};

		// Using Java8 Lambda Functions
		Comparator<Apple> byWeightLambda = Comparator.comparingInt(Apple::getWeight);

	}
}

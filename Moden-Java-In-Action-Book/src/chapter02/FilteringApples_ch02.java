package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Comparator.comparing;

public class FilteringApples_ch02 {

	enum Color {
		RED, GREEN
	}

	public static void main(String... args) {
		List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN), new Apple(155, Color.GREEN),
				new Apple(120, Color.RED));
		
		
		inventory.sort(comparing(Apple::getWeight).reversed());
		inventory.forEach(System.out::println);
		System.out.println();

		// [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
		List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
		System.out.println(greenApples);

		// [Apple{color=RED, weight=120}]
		List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
		System.out.println(redApples);

		// [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
		List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
		System.out.println(greenApples2);

		// [Apple{color=GREEN, weight=155}]
		List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
		System.out.println(heavyApples);

		// []
		List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
		System.out.println(redAndHeavyApples);

		// [Apple{color=RED, weight=120}]
		List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple a) {
				return a.getColor() == Color.RED;
			}
		});
		System.out.println(redApples2);
	}

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor() == Color.GREEN) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor() == color) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static class Apple {

		private int weight = 0;
		private Color color;

		public Apple(int weight, Color color) {
			this.weight = weight;
			this.color = color;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public String toString() {
			return String.format("Apple{color=%s, weight=%d}", color, weight);
		}

	}

	interface ApplePredicate {

		boolean test(Apple a);

	}

	static class AppleWeightPredicate implements ApplePredicate {

		@Override
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}

	}

	static class AppleColorPredicate implements ApplePredicate {

		@Override
		public boolean test(Apple apple) {
			return apple.getColor() == Color.GREEN;
		}

	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {

		@Override
		public boolean test(Apple apple) {
			return apple.getColor() == Color.RED && apple.getWeight() > 150;
		}

	}

}

package LambdaExpression_DirtyPractice;

import java.util.Arrays;
import java.util.List;

// LAB#5 Task #2: Write a flexible prettyPrintApple method
class AppleFancyFormatter implements AppleFormatter {
	public String accept(Apple apple) {
		String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
		return "A " + characteristic + " " + apple.getColor() + " apple";
	}
}

class AppleSimpleFormatter implements AppleFormatter {
	public String accept(Apple apple) {
		return "An apple of " + apple.getWeight() + "g";
	}
}

public class PrettyPrintApple_LE {
	public static void main(String[] agrs) {
		List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
		inventory.add(new Apple(60, "brown"));

		iMyInterface myInterface = (text) -> {
			System.out.println(text);
		};
		myInterface.testDisplay(" test a simple text");

		// LAB#5 Task #2: Write a flexible prettyPrintApple method
		prettyPrintApple(inventory, new AppleFancyFormatter());
		prettyPrintApple(inventory, new AppleSimpleFormatter());
	}

	public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
		for (Apple apple : inventory) {
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}

}

class Apple {

	private int weight = 0;
	private String color = "";

	public Apple(int weight, String color) {
		this.weight = weight;
		this.color = color;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return String.format("Apple{color='%s', weight=%d}", color, weight);
	}

}
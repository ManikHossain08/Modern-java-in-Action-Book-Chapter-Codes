package QuizTasks_CleanCode;

import java.util.Arrays;
import java.util.List;
import chapter01.Apple;

// this is somehow call STRATEGY DESING PATTERN

public class PrettyPrintApple {

	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

		// LAB#5 Task #2: Write a flexible prettyPrintApple method
		prettyApple(inventory, new AppleFancyFormatter());

	}

	public static void prettyApple(List<Apple> inventory, AppleFormatter formatter) {
		for (Apple apple : inventory) {
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}

}

interface AppleFormatter {
	String accept(Apple a);
}

//LAB#5 Task #2: Write a flexible prettyPrintApple method
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

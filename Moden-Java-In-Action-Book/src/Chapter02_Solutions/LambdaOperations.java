package Chapter02_Solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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


public class LambdaOperations {

    public static void main(String... args) {

        List<Apple> inventory = new ArrayList<>();
        String[] colors = {"red", "green", "blue"};

        // Creating an inventory of apple with decreasing order of weight
        for (int i=9; i>=1; i--) {
            Apple apple = new Apple(i, colors[i%3]);
            inventory.add(apple);
        }

        prettyPrintApple(inventory, new AppleFancyFormatter());

        // Filtering using Java8
        List<Apple> redApples = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        // Filtering red apples using generic filter
        List<Apple> redApples2 = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));

        // Sorting an inventory based on color
        Collections.sort(inventory, (a1, a2) -> a1.getColor().compareTo(a2.getColor()));

    }

    public interface ApplePredicate {
        boolean test(Apple apple);
    }

    public class AppleHeavyWeightPredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public class AppleGreenColorPredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    // Filter using predicate
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    // Generalizing the Predicate
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }

        return result;
    }

    public interface AppleFormatter{
        String accept(Apple a);
    }

    public class AppleSimpleFormatter implements AppleFormatter{
        @Override
        public String accept(Apple apple) {
            return "An apple of " + apple.getWeight() + "g";
        }
    }

    public static class AppleFancyFormatter implements AppleFormatter{
        @Override
        public String accept(Apple apple) {
            String characterstic = apple.getWeight() > 150 ? "heavy" : "light";
            return "A " + characterstic + " " + apple.getColor() + " apple";
        }
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for (Apple apple : inventory) {
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }

    // Filtering based on color
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {

        List<Apple> filteredApples = new ArrayList<>();

        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                filteredApples.add(apple);
            }
        }

        return filteredApples;
    }

    // Filtering based on weight
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {

        List<Apple> filteredApples = new ArrayList<>();

        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                filteredApples.add(apple);
            }
        }

        return filteredApples;
    }

    // Adding both the filters. Verbose code
    public static List<Apple> filterApplesVerbose(List<Apple> inventory, int weight, String color, boolean flag) {

        List<Apple> filteredApples = new ArrayList<>();

        for (Apple apple : inventory) {
            if (flag && apple.getColor().equals(color) || !flag && apple.getWeight() > weight) {
                filteredApples.add(apple);
            }
        }

        return filteredApples;
    }
}
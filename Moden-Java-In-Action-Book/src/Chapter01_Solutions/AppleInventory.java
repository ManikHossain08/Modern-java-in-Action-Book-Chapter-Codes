package Chapter01_Solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AppleInventory {

    public static void main(String... args) {

        List<Apple> inventory = new ArrayList<>();
        String[] colors = {"red", "green", "blue"};

        // Creating an inventory of apple with decreasing order of weight
        for (int i=9; i>=1; i--) {
            Apple apple = new Apple(i, colors[i%3]);
            inventory.add(apple);
        }

        // Sorting using older method of Collections API
        sortOldMethod(inventory);

        // Sorting using Java8 method
        inventory.sort(Comparator.comparing(Apple::getWeight));

        // Filtering green apples using old method
        List<Apple> greenApples1 = filterGreenApples(inventory);

        // Filtering green apples using Java 8 method
        List<Apple> greenApples2 = filterApples(inventory, AppleInventory::isGreenApple);

    }

    private static void sortOldMethod(List<Apple> inventory) {

        Collections.sort(inventory, new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2){
                return a1.getWeight() - a2.getWeight();
            }
        });
    }

    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> greenApples = new ArrayList<>();

        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                greenApples.add(apple);
            }
        }

        return greenApples;
    }

    private static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }
}

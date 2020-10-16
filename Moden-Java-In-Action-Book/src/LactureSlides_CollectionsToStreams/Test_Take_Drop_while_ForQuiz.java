package LactureSlides_CollectionsToStreams;

import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.stream.Stream;

public class Test_Take_Drop_while_ForQuiz {
	public static void main(String[] args) {
		
		List<Dish> menu = Dish.menu;
		
		Stream.of(1 , 2, 3, 5, 7, 9, 13, 4, 6, 10, 17)
		.dropWhile(p -> p%2 == 1)
		.forEach(System.out::println);
		
		// takeWhile
		// Find elements that have fewer than 320 calories:
		List<Dish> slicedMenu1 = menu.stream().takeWhile(dish -> dish.getCalories() < 320).collect(toList());
		// dropWhile
		// Find elements that have more than 320 calories:
		List<Dish> slicedMenu2 = menu.stream().dropWhile(dish -> dish.getCalories() < 320).collect(toList());
		
	}
}

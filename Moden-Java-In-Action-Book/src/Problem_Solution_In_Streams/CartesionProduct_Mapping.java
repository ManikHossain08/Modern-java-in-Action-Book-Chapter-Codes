package Problem_Solution_In_Streams;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CartesionProduct_Mapping {

	public static void main(String[] args) {

		// Task #2: Mapping (1)
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squares = numbers.stream().map(x -> x * x).collect(toList());

		squares.forEach(p -> {
			System.out.println(p);
		});
		
		

		// Task #2: Mapping (2)
		List<Integer> aList = Arrays.asList(1, 2, 3);
		List<Integer> bList = Arrays.asList(3, 4);

		List<List<Integer>> product = aList.stream()
				.flatMap(a -> bList.stream().flatMap(b -> Stream.of(Arrays.asList(a, b)))).collect(toList());

		product.forEach(p -> {
			System.out.println(p);
		});
		
		

		// Task #2: Mapping (3)
		List<List<Integer>> pairsum = product.stream().filter(list -> (list.get(0) + list.get(1)) % 3 == 0)
				.collect(toList());
		pairsum.forEach(p -> {
			System.out.println(p);
		});

		// Flattening Stream : example code and practicing
		// https://www.geeksforgeeks.org/stream-flatmap-java-examples/
		// flatMap() merge the data altogether like below.

		List<String> words = new ArrayList<String>();
		words.add("Hello");
		words.add("Word");

		List<String> a = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct()
				.collect(toList());
		System.out.println(a);

		/*
		 * The Structure before flattening is : [[5, 7, 11, 13], [1, 3, 5], [2, 4, 6,
		 * 8]] The Structure after flattening is : [5, 7, 11, 13, 1, 3, 5, 2, 4, 6, 8]
		 * 
		 * // read for more details:
		 * https://www.geeksforgeeks.org/stream-flatmap-java-examples/
		 */

// prints:
//	              [1, 4]
//	              [1, 5]
//	              [1, 6]
//	              [2, 4]
//	              [2, 5]
//	              [2, 6]
//	              [3, 4]
//	              [3, 5]
//	              [3, 6]

	}
}

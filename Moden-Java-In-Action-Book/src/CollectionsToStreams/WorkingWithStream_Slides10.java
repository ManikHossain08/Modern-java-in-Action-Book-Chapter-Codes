package CollectionsToStreams;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

// https://www.tutorialspoint.com/differences-between-takewhile-and-dropwhile-methods-in-java-9

/*
 * Summary: Streams
 * 
		• The Streams API lets you express complex data processing queries.
		• You can filter and slice a stream using filter, distinct, skip, and limit
		• You can extract or transform elements of a stream using map and flatMap
		• You can find elements in a stream using the findFirst and findAny
		methods
		• You can match a given predicate in a stream using allMatch, noneMatch,
		and anyMatch
		• These methods make use of short-circuiting
		• You can combine all elements of a stream using reduce
		• Some operations such as filter and map are stateless; some operations
		such as reduce store state to calculate a value and are called stateful
		• There are three primitive specializations of streams: IntStream,
		DoubleStream, and LongStream
		• Streams can be created from a collection or from values, arrays, files, and
		specific methods such as iterate and generate
		• An infinite stream is a stream that has no fixed size
 */

public class WorkingWithStream_Slides10 {
	
	 private static String MacOSXPathToReadFile = "/Users/info.txt";
	
	public static void main(String[] args) {
		List<Dish> menu = Dish.menu;

		// ---- From external iteration to internal iteration -----//

		// Collections API: this is the example of 'External Iteration '
		List<Dish> vegitarianDishes = new ArrayList<>();
		for (Dish dish : menu) {
			if (dish.isVegetarian())
				vegitarianDishes.add(dish);
		}

		// Stream API: this is the example of 'Internal Iteration' because it is doing
		// by the stream.
		List<Dish> vegitarianDishes_stream = menu.stream().filter(Dish::isVegetarian).collect(toList());

		vegitarianDishes_stream.stream().forEach(System.out::println);

		// ---- Filtering unique elements -----//
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

		// ---- Slicing a stream -----//
		// New special menu
		List<Dish> specialMenu = Arrays.asList(new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("chicken", false, 400, Dish.Type.MEAT), new Dish("onion", true, 120, Dish.Type.OTHER),
				new Dish("french fries", true, 530, Dish.Type.OTHER));

		// Select dishes with fewer than 320 calories?

		List<Dish> less230cal = specialMenu.parallelStream()
				// .filter(Dish::getCalories < 320) // this statement will not work because
				// there is a
				// condition with method refference so we need to use LAMBDA EXPRESSION FOR
				// EXTRA CONDITION
				.filter(P -> P.getCalories() < 320).collect(toList()); // if we use collect we cannot use forEach in the
																		// end
		// .forEach(System.out::println); if we use forEach then we cannot use any
		// return type to
		// store the value in it like 'List<Dish>'. this would be wrong to use list and
		// store it
		// we have to run this 'forEach(System.out::println)' terminal stream operation
		// directly.
		System.out.println("less230cal using lambda expression and FILTER operator");
		less230cal.forEach(System.out::println); // after collect we can use this statement legally.

		// -------- Using takeWhile and dropWhile (Java 9) ---------//

		// Find elements that have fewer than 320 calories:
		List<Dish> less230cal1 = specialMenu.stream().takeWhile(dish -> dish.getCalories() < 320).collect(toList());
		System.out.println("less230cal using lambda expression and takeWhile operator");
		less230cal1.forEach(System.out::println);
		System.out.println();

		List<Dish> less230cal2 = specialMenu.stream().dropWhile(dish -> dish.getCalories() < 320).collect(toList());
		System.out.println("less230cal using lambda expression and dropWhile operator");
		less230cal2.forEach(System.out::println);
		System.out.println();

		// takeWhile
		// Find elements that have fewer than 320 calories:
		List<Dish> slicedMenu1 = menu.stream().takeWhile(dish -> dish.getCalories() < 320).collect(toList());
		slicedMenu1.forEach(System.out::println);
		System.out.println();

		// dropWhile
		// Find elements that have more than 320 calories:
		List<Dish> slicedMenu2 = menu.stream().dropWhile(dish -> dish.getCalories() < 320).collect(toList());
		slicedMenu2.forEach(System.out::println);
		System.out.println();

		// Flattening Stream
		List<String> words = new ArrayList<String>();
		words.add("Hello");
		words.add("Word");

		List<String> a = words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct()
				.collect(toList());
		System.out.println(a);

		// Is there at least one vegetarian dish on the menu
		// here 'anyMatch' is a terminal operation, so we cannot add anything after this operation.
		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("Yes, there is atleast one vegitearian.");
		}

		// Is the menu healthy ()
		// here 'allMatch' is a terminal operation, so we cannot add anything after this operation.
		if (menu.stream().allMatch(Dish::isVegetarian)) {
			System.out.println("Yes, all the dishes is vegitearian.");
		} else {
			System.out.println("No, not all the dishes is vegitearian.");
		}

		// Is the menu healthy ()
		// here 'allMatch' is a terminal operation, so we cannot add anything after this operation.
		// 'allMatch' check the condition for all the record and if all record satisfy with the condition then return true else false
		if (menu.stream().allMatch(cal -> cal.getCalories() < 1000)) {
			System.out.println("Yes, all the dishes is less than 1000 calories.");
		} else {
			System.out.println("No, not all the dishes is less than 1000 calories.");
		}
		
		// is the menu healthy ()
		if (menu.stream().noneMatch(cal -> cal.getCalories() >= 1000)) {
			System.out.println("Yes, all the dishes is less than 1000 calories.");
		} else {
			System.out.println("No, not all the dishes is less than 1000 calories.");
		}
		
		
		/*
		 *  Note: all these terminal operations allMatch, anyMatch, noneMatch are short-circuiting
		 *  
		 *  read short-circuiting (or lazy) evaluation.... with boolean condition.
		 */
		
		// ---- finding an element ---- //
		// find any dish that is vegetarian 
		// 'Optional' will handle values as ‘available’ or ‘not available’ instead of checking null values.
		// if nothing found show the result 'Optional.empty' and never check null and would not show null pointer exception
		
		Optional<Dish> anyDish = menu.stream()
								 .filter(Dish::isVegetarian)
								 .findAny();
		System.out.println(anyDish);
		
		menu.stream()
				 .filter(d -> d.getCalories() > 1000)
				 .findAny()
				 .ifPresent(d -> System.out.println(d.getName())); // if found print the result else do nothing
		
		boolean ispresent = menu.stream()
		 .filter(d -> d.getCalories() > 100)
		 .findAny()
		 .isPresent();
	   //.ifPresent(d -> System.out.println(d.getName()));
		System.out.println(ispresent);
		
		
		// ---- FINDING THE FIRST ELEMENT -------- //
		Optional<Integer> findFirstDish = menu.stream()
									   .map(cal -> cal.getCalories() * 2) // converting the value from one to another form.
									   .filter(cal -> cal % 2 == 0)
									   .findFirst();
		System.out.println("using findFirst terminal operation: " + findFirstDish);
		
		/*
		 * Difference between findFirst and findAny 
		 * findAny: findAny can process a stream in parallel.
		 * findFirst: but findFirst CANNOT process a stream in parallel.
		 */
		
		// ---- SUMMING ELEMENTS USING 'reduce' -------- //
		
		int reduceToInt = specialMenu.stream()
						  .map(cal->cal.getCalories())
						  .reduce(0, (c1, c2) -> c1 + c2); // 0 is initial value of sum then it recursively adding two value 
		System.out.println("SUMMING ELEMENTS USING 'reduce': " + reduceToInt);
		
		// --- NO INITIAL VALUE (OVERLOADED REDUCE) ---// 
		Optional<Integer> reduceToInt2 = specialMenu.stream()
				  .map(cal->cal.getCalories())
				  .reduce((c1, c2) -> c1 + c2); // notice no initial value like before so the terminal operator will 
												// produce optional type value but using directly get() we can get int val.
		System.out.println("SUMMING ELEMENTS USING 'reduce': " + reduceToInt2.get());
		
		// --- COMPUTE SUM, min, max IN PARALLEL ---//
		int sum = List.of(1,2,3,4,5,6,7).parallelStream()
				  .reduce(0, Integer::sum);
		System.out.println("COMPUTE SUM IN PARALLEL USING 'reduce': " + sum);
		
		Optional<Integer> min = List.of(1,2,3,4,5,6,7).parallelStream()
				  .reduce(Integer::min);
		System.out.println("COMPUTE min IN PARALLEL USING 'reduce': " + min.get());
		
		Optional<Integer> max = List.of(1,2,3,4,5,6,7).parallelStream()
				  .reduce(Integer::max);
		System.out.println("COMPUTE max IN PARALLEL USING 'reduce': " + max.get());
		
		Optional<Integer> maxCaloriedDish = specialMenu.parallelStream()
											 .map(cal -> cal.getCalories())
							  				 .reduce(Integer::max);
		System.out.println("COMPUTE max Caloried Dish using parallel stream and 'reduce': " + maxCaloriedDish.get());
		
		/*
		 * Stream OPeration: Stateful & Stateless operations
		 * 
		 * Stateless: - take each input element and produce zero or one result;
		 * 			  - Example, map (take input and produce corresponding output), filter (find match return zero or one result)
		 * 			  - Do not need internal state (unless added in lambda)
		 * 
		 * Statefull: - operations need to keep internal state to accumulate result
		 * 			  - Example, reduce, sum, max, min
		 * 			  - internal state is of 'bounded size' (independent of stream size)
		 * 			  - stateful- bounded need an intermediate value.
		 * 
		 * Unbounded Stateful Operation:
		 * 			  - operations require all elements in the stream to be buffered
		 * 			  - Example, sorted, distinct
		 * 			  - internal state is of unbounded size 
		 * 			  - problematic for large input size.
		 * 
		 */

		// ---- Example: Traders executing transactions -----//
		/*
		Your job is to write programs that can answer:
			1 Find all transactions in the year 2011 and sort them by value (small to high).
			2 What are all the unique cities where the traders work?
			3 Find all traders from Cambridge and sort them by name.
			4 Return a string of all traders’ names sorted alphabetically.
			5 Are any traders based in Milan?
			6 Print all transactions’ values from the traders living in Cambridge.
			7 What’s the highest value of all the transactions?
			8 Find the transaction with the smallest value.
			
			see 'Problem_Solution_Streams' class
		*/
		
		
		// ---- Numeric Streams ----//
		
		// Compute total number of calories in a menu
		int calories = menu.stream()
						.map(Dish::getCalories)
						.reduce(0, Integer::sum);
		
		System.out.println(calories);
		/*
		 * Issues of this code :
			• Each Integer needs to be unboxed (costly!)
			• Cannot call sum directly, i.e. like this code,
			
								int calories = menu.stream()
												.map(Dish::getCalories)
												.sum();
												
			this code does not work.
			
			- Solution: primitive stream specializations.
		 */
		
		
		
		// -------- Primitive stream specializations ----------//
		// Mapping to a numeric stream
			int calory = menu.stream()
						.mapToInt(Dish::getCalories)
						.sum();
			System.out.println(calory);
			
			/*
			 * mapToInt:
					• returns an IntStream (rather than Stream<Integer>)
					• can call sum on IntStream
					• other operations: max, min, average
					• empty stream: result = 0
			 */
		
		// ------ Process the optionalInt -----//
		OptionalInt	intval = menu.stream()
					.mapToInt(Dish::getCalories)
					.max(); // without 'mapToInt' operator max() will not work  
		System.out.println("Process the optionalInt : " + intval);	
		
		
		
		// ------- Streams from Values ----------//
		// Example: Stream of strings
		Stream<String> streamOfStr = Stream.of("Java", "Basic", "Learning", "By books modern java 8 in action");	
		streamOfStr.map(String::toUpperCase).forEach(System.out::println);
		Stream<String> streamOfEmpty = Stream.empty();
		System.out.println(streamOfEmpty);
		
		// Example: Stream of primitive ints
		int[] nums = {2, 3, 5, 7, 11, 13};
		int sumUp = Arrays.stream(nums).sum(); 
		System.out.println(sumUp);	
		
		
		
		// Example: Count lines in a file
		try {
			long lineCount = Files.lines(Paths.get(MacOSXPathToReadFile)).count();
			System.out.println(lineCount);	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Example: Count unique words in a file
									 
		try {
			Stream<String> lines = Files.lines(Paths.get(MacOSXPathToReadFile), Charset.defaultCharset());
			System.out.println("Lines are: "+ lines);	
			
			lines
			.flatMap( word -> Arrays.stream(word.split(" "))) // if split by space then we will get each word, keeping no space 
															  // will be splitted by each character
			.distinct()
			.forEach(System.out::println); // if we use count() here we will get the number of unique words/chars like below commented code
			
			//			long uniqueWordCount = lines
			//										.flatMap( word -> Arrays.stream(word.split("")))
			//										.distinct()
			//										.count();
			//			System.out.println("# unique Word Count: "+ uniqueWordCount);	
			//Stream
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		// ------- Streams from functions: creating infinite streams! ---------//
			Stream.iterate(0, n -> n + 1)
				   .limit(10) // without limit this will be infinite loop
				   .forEach(System.out::println);	
			
			/*
			 * iterate: 
					• takes initial value and a lambda (UnaryOperator<T>)
					• infinite stream: creates values on demand (unbounded)
					• usually should be bounded through limit(n)
					• computed sequentially: new result depends on previous
			 */
		
		// ----- Generate five random numbers in [0; 1) -------//
			
			Stream.generate(Math::random)
			.limit(5)
			.forEach(System.out::println);
	}
}

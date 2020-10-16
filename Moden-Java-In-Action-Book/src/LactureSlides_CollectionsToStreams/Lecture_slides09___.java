package LactureSlides_CollectionsToStreams;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import LactureSlides_CollectionsToStreams.Dish.Type;

/*
 * Stream: A stream is a sequence of elements from a source that supports data processing operations.
 * 
 * Stream Characteristics
	Details
	Sequence of elements: A stream provides an interface to a sequenced set of
	values of a specific element type. Unlike collections, they focus on
	expressing computations (e.g., filter, sorted, map).
	Collections are about data; streams are about computations.
	
	Source: Streams consume from a data-providing source such as collections,
	arrays, or I/O resources. Generating a stream from an ordered
	collection preserves the ordering.
	
	Data processing operations: Streams support database-like operations and
	common operations from functional programming languages to
	manipulate data, such as filter, map, reduce, find, match,
	sort, and so on. Stream operations can be executed either
	sequentially or in parallel.
	
	Pipelining: Many stream operations return a stream themselves, allowing
	operations to be chained and form a larger pipeline. This enables
	certain optimizations, such as laziness and short-circuiting.
	
	Internal iteration: In contrast to collections, which are iterated explicitly using an
	iterator, stream operations do the iteration behind the scenes for you.
	
	A LAZY CONSTRUCTIONS MEANS VALUES ARE COMPUTED WHEN WE NEEDED (AS NEEDED BASIC);
	
	
	Summary Streams:
	• A stream is a sequence of elements from a source that supports data
	processing operations.
	• Streams make use of internal iteration: the iteration is abstracted away through
	operations such as filter, map, and sorted.
	• There are two types of stream operations: intermediate and terminal
	operations.
	• Intermediate operations (e.g., filter, map) return a stream and can be
	chained together. They’re used to set up a pipeline of operations but don’t
	produce any result.
	• Terminal operations such as forEach and count return a nonstream value
	and process a stream pipeline to return a result.
	• The elements of a stream are computed on demand.
	
	
 * 
 */

public class Lecture_slides09___ {
	public static void main(String[] args) {
		List<Dish> menu = Dish.menu;
		
		// ------- With Java 8 Streams -------//
		System.out.println("ALL FOOD MENU: ");
		menu.forEach( food -> System.out.println(food));
		
		
		List<String> lowCalories = menu.stream()
								 .filter(g -> g.getCalories() < 400) // filtering
								 .sorted(comparing(Dish::getCalories)) // sorting
								 .map(Dish::getName) // mapping by name from calories after filter and sort.
								 .collect(toList()); // CONVERT STREAM TO ANOTHER list after collect
		
		System.out.println("\nAll low calories food menu after filter using stream: ");
		lowCalories.forEach(calories -> System.out.println(calories)); 
		
		// ------- Multicore architecture: Run in parallel --------// 
		/*
		    Stream Pipelines: Chaining stream operations forming a stream pipeline
		 */
		
		System.out.println("\nLow calories food menu after filter using PARALLEL stream: ");
		List<String> lowCaloricDishesName = menu.parallelStream()
											.filter(d -> d.getCalories() < 400)
											.sorted(comparing(Dish::getCalories))
											.map(Dish::getName)
											.collect(toList());
		
		lowCaloricDishesName.forEach(calories -> System.out.println(calories)); 
		
		
		// ------- Group dishes by type into a Map ---------//
		System.out.println("\nGroup dishes by type into a Map: ");
		
		Map<Dish.Type, List<Dish>> GroupedDishes = menu.parallelStream()
												   .collect(groupingBy(Dish::getType));
		
		for (Entry<Type, List<Dish>> dishes : GroupedDishes.entrySet()) {
			System.out.println("Group :"+dishes.getKey());
			for (Dish dis : dishes.getValue()) {
				System.out.print(dis.getName()+ ", ");
			}
			System.out.println();
		}
		
		/*
			New Streams API (java.util.stream.Stream): With Java 8 you can write code that is
			DECLARATIVE: More concise and readable
			COMPOSABLE: Greater flexibility
			PARALLELIZABLE: Better performance
		 */
		
		// ---- Stream Example: Filtering a menu using a stream to find out three high-calorie dish names ----//
		
		List<String> threehighCaloricDishes = menu.parallelStream()
												  .filter(c -> c.getCalories() >= 400)
												  .map(m -> m.getName())
												  .limit(3)
												  .collect(toList());
		// WITHOUT SORTING THE LIST THIS CODE WILL ONLY TAKE 3 DISHES FROM THE ORIGINAL LIST THAT HAVE BEEN FILTERED
		// FROM THE LIST ACCORDINGLY.
		
		System.out.println();
		System.out.println(threehighCaloricDishes);
		
		List<String> top3highCaloricDishes = menu.parallelStream()
				  .sorted(comparing(Dish::getCalories).reversed())
				  .filter(c -> c.getCalories() >= 400)
				  .map(m -> m.getName())
				  .limit(3)
				  .collect(toList());
		
		// BUT WITH SORTING WE WILL GET TOP 3 HIGH CALORIED DISHES FROM THE SORTED LIST
		
		System.out.println();
		System.out.println(top3highCaloricDishes);
		
		// Stream Traversal: A stream can be traversed only once (BufferReader also do the same it will not traverse again)
		
		System.out.println("\nStream Traversal: ");
		
		List<String> title = Arrays.asList("Java8", "In", "Action");
		Stream<String> s = title.stream();
		s.forEach(System.out::println);
		// s.forEach(System.out::println); // THIS STATEMENT WILL NOT EXECUTE AGAIN BECAUSE stream has already been operated upon or closed
		
		/*
		 * Syntactic Sugar: The for-each construct is an example of so-called syntactic sugar.
		 * COLLECTIONS USE EXTERNAL ITERATION BUT STREAM USE INTERNAL ITERATION.
		 * 
		 * Two groups of operations: 1> Intermediate & 2> Terminal
			• filter, map, limit etc. can be connected to form a pipeline (INTERMEDIATE)
			• collect causes the pipeline to be executed and closes it (TERMINAL)
		 * 
		 */
		
		
		// Intermediate operations: Understanding lazy execution
		System.out.println("\nIntermediate operations- Understanding lazy execution: ");
		List<String> names = menu.stream()
							.filter(d -> {
								System.out.println("filtering" + d.getName());
								return d.getCalories() > 300; })
							.map(d -> {
								System.out.println("mapping" + d.getName());
								return d.getName(); })
							.limit(3)
							.collect(toList());
		//System.out.println(names);
		names.stream().forEach(System.out::println);
		
		
		
		System.out.println("\nTerminal operations EXAMPLE: ");
		/*
		 * Terminal operations: 
		    Terminal operations produce a result from a stream pipeline. A result is any
		 	non-stream value, e.g., List, Integer, void. SEE THE EXAMPLE BELOW
		 */
		menu.stream().forEach(System.out::println);
		
		// What are the intermediate and terminal operations in this pipeline?
		long count = menu.stream()
				.filter(d -> d.getCalories() > 300) //intermediate
				.distinct() //intermediate
				.limit(3) //intermediate
				.count(); // terminal
		System.out.println(count);
		
		
		/*
		 * In general, you need three items:
			• A data source (such as a collection) to perform a query on
			• A chain of intermediate operations that form a stream pipeline
			• A terminal operation that executes the stream pipeline and produces a result
			Similar idea to the Builder design pattern.
			
			INTERMEDIATE STREAM OPERATIONS : filter, map, limit, sorted, distinct
			TERMINAL STREAM OPERATION: forEach, count, collect
			// note: *** read page 30 of slides09//.****
			
		 */
		
		
		
	}
}

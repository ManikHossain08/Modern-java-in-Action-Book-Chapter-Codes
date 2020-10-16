package Problem_Solution_In_Streams;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.joining;
import static java.util.Comparator.comparing;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
 * 
 Your job is to write programs that can answer:
 
	1 Find all transactions in the year 2011 and sort them by value (small to high).
	2 What are all the unique cities where the traders work?
	3 Find all traders from Cambridge and sort them by name.
	4 Return a string of all traders’ names sorted alphabetically.
	5 Are any traders based in Milan?
	6 Print all transactions’ values from the traders living in Cambridge.
	7 What’s the highest value of all the transactions?
	8 Find the transaction with the smallest value.
	9 For each trader, return the number of lowercase letters in the name (hint: look at the chars method on String).
	10 Find the city String with the largest number of lowercase letters from all the cities in the transaction list.\n 
	   Experiment with returning an Optional<String> to account for the case of an empty input list.
 */

public class Problem_Solution_Streams {
	public static void main(String[] args) {

		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		transactions.forEach(System.out::println);
		
		
		System.out.println("\n1. Find all transactions in the year 2011 and sort them by value (small to high):");
		List<Transaction> trans2011 = transactions.stream()
									  .filter(year -> year.getYear() == 2011)
									  .sorted(comparing(Transaction::getValue))
									  .collect(toList());		
		trans2011.forEach(System.out::println);		
		
		System.out.println("\n2. What are all the unique cities where the traders work?:");
		List<String> uniqueCities = transactions.stream()
									  .map(city -> city.getTrader().getCity())
									  .distinct()
									  .collect(toList());		
		uniqueCities.forEach(System.out::println);
		
		
		System.out.println("\n2. What are all the unique cities where the traders work?: [ALTERNATIVE SOLUTION]");
		Set<String> uniqueCitiesBySets = transactions.stream()
									  .map(city -> city.getTrader().getCity())
									  .collect(toSet());		
		uniqueCitiesBySets.forEach(System.out::println);
		
		
		
		System.out.println("\n3. Find all traders from Cambridge and sort them by name.:");
		List<Trader> allTraders = transactions.stream()
									  .map(Transaction::getTrader)
									  .filter(trader -> trader.getCity().contentEquals("Cambridge"))
									  .sorted(comparing(Trader::getName))
									  .distinct()
									  .collect(toList()); 
		allTraders.forEach(System.out::println);
		
		
		System.out.println("\n4. Return a string of all traders’ names sorted alphabetically.:[MY SOLUTION]");
		 		transactions.stream()
									  .map(name -> name.getTrader().getName())
									  .sorted()
									  .distinct()
									  .forEach(p -> System.out.print(p +" "));
		 
		System.out.println("\n4. [ALTERNATIVE SOLUTION-PROFESSOR01] Return a string of all traders’ names sorted alphabetically.: ");
		String allTradersStr  = transactions.stream()
									  .map(name -> name.getTrader().getName())
									  .distinct()
									  .sorted()
									  .reduce("", (a, b) -> a + b);
		 							  
		 System.out.println(allTradersStr);
		 
		 
		System.out.println("\n4. [ALTERNATIVE SOLUTION-PROFESSOR02] Return a string of all traders’ names sorted alphabetically.: ");
			String allTradersStr1  = transactions.stream()
										  .map(name -> name.getTrader().getName())
										  .distinct()
										  .sorted()
										  .collect(joining());
			 							  
			 System.out.println(allTradersStr1);
		 
		System.out.println("\n5. Are any traders based in Milan?:");
		Optional<Transaction> anyMilanTraders = transactions.stream()
										  .filter(city -> city.getTrader().getCity() == "Milan")
										  .findAny();
		
		System.out.println( anyMilanTraders.isPresent() + " "+ anyMilanTraders.get());								  
		
		
		System.out.println("\n5. [ALTERNATIVE SOLUTION-PROFESSOR] Are any traders based in Milan?:");
		boolean anyMilanTraders1 = transactions.stream()
										  .anyMatch(city -> city.getTrader().getCity() == "Milan");
		
		System.out.println( anyMilanTraders1);								  
		
		
		
		
		System.out.println("\n6. Print all transactions’ values from the traders living in Cambridge.");
		 				transactions.stream()
										  .filter(city -> city.getTrader().getCity() == "Cambridge")
										  .map(val -> val.getValue())
										  .forEach(System.out::println);
		
		
		System.out.println("\n7. What’s the highest value of all the transactions?:");
		Optional<Integer> findMaxTrans = transactions.stream()
										  .map(val -> val.getValue())
										  .reduce(Integer::max);
		System.out.println( "Max Transactions is: "+ findMaxTrans.get());	
		
		
		System.out.println("\n8. [not exact done by me] Find the transaction with the smallest value.");
		Optional<Integer> findMinTrans = transactions.stream()
										  .map(val -> val.getValue())
										  .reduce(Integer::min);
		System.out.println( "Min Transactions is: "+ findMinTrans.get());
		
		
		System.out.println("\n8.[EXACT-SOLUTION-PROFESSOR] Find the transaction with the smallest value.");
		Optional<Transaction> findMinTrans1 = transactions.stream()
										  .min(comparing(Transaction::getValue));
										  
		System.out.println( findMinTrans1);
		
		
		System.out.println("\n9. For each trader, return the number of lowercase letters in the name (hint: look at the chars method on String).: ");
		List<Integer> leterCount  = transactions.stream()
									  .map(name -> name.getTrader().getName().substring(1))
									  .map(a -> a.length())
									  .collect(toList());
		leterCount.forEach(System.out::println);
		
		
		System.out.println("\n10 Find the city String with the largest number of lowercase letters from all the cities in the transaction list.\\n \n" + 
				"	   Experiment with returning an Optional<String> to account for the case of an empty input list.: ");
		int maxLengthCity  = transactions.stream()
									  .map(Transaction::getTrader)
									  .map(city -> city.getCity().substring(1))
									  .mapToInt(a -> a.length())
									  .max().getAsInt();
		
		Optional<String> citymaxLower = transactions.stream()
										.map(Transaction::getTrader)
										.filter(city -> city.getCity().substring(1).length() == maxLengthCity)
										.map(city -> city.getCity())
										.findFirst();
		System.out.println(citymaxLower.get());
		
		
		// Filter list of transactions by amount and group by currency
		
//		Map<Currency, List<Transaction>> transactionsByCurrencies =
//		transactions.stream()
//		.filter((Transaction t) -> t.getPrice() > 1000)
//		.collect(groupingBy(Transaction::getCurrency));
		
		
		
	}
}

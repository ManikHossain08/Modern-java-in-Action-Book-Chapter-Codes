package Problem_Solution_In_Streams;

import java.util.stream.Stream;

public class FiboNacci_Stream {
	public static void main(String[] args) {
		
		int n = 12;
		
		
		
		Stream.iterate(new long[]{0, 1}, p -> new long[]{p[1], p[0] + p[1]})
	    	   .limit(n+1)
	    	   .skip(n)
	    	   .forEach(p->System.out.println(p[0])); // print nth element of the series
		
		findFibonacciSeries(n);
		
	}

	private static void findFibonacciSeries(int n) {
		//int prevVal = 0; 
//		Stream.iterate(1, sum -> {  sum = sum + prevVal; prevVal = sum - prevVal; return sum;})
//		  .limit(10)
//		  .forEach(System.out::println);	
		
		Stream.iterate(new long[]{0, 1}, p -> new long[]{p[1], p[0] + p[1]})
			    .limit(n + 1)
			    .forEach(p->System.out.println(p[0]));	
	}
}

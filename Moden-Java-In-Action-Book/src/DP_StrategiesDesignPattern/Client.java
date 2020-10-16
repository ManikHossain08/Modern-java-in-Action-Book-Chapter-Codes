package DP_StrategiesDesignPattern;

public class Client {

	public static void main(String[] args) {
		
		// https://refactoring.guru/design-patterns/strategy/java/example
		// https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm
		// https://dzone.com/articles/strategy-pattern-using-lambda
		// https://www.javacodegeeks.com/2016/01/java-8-lambda-expression-design-patterns-strategy-design-pattern.html
		// https://www.baeldung.com/java-strategy-pattern

		// Type-1
		Context c = new Context(new OperationAdd());
		System.out.println("10 + 5 = " + c.executeStrategy(10, 5));
		
		// Type-2
		OperationMultiply addition = new OperationMultiply();
		System.out.println("10 + 5 = " + addition.doOperation(10, 5));
		
	}

}

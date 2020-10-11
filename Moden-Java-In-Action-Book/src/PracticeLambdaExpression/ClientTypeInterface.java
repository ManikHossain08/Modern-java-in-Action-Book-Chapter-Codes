package PracticeLambdaExpression;

public class ClientTypeInterface {

	public static void main(String[] args) {

		iStringLengthLambda getLentghLambda = s -> s.length();
		
		// Way: 1
		System.out.println(getLentghLambda.getLengthOFString("Hellow world"));

		// Way: 2
		System.out.println(printlambdaValue(getLentghLambda));
		
		// Way: 3
		System.out.println(printlambdaValue( "Hellow world with input String", s -> s.length()));

	}

	public static int printlambdaValue(String inputString, iStringLengthLambda lambda) {
		return lambda.getLengthOFString(inputString);
	}
	
	public static int printlambdaValue(iStringLengthLambda lambda) {
		return lambda.getLengthOFString("Hellow world without input String");
	}

	public interface iStringLengthLambda {
		int getLengthOFString(String s);
	}

}

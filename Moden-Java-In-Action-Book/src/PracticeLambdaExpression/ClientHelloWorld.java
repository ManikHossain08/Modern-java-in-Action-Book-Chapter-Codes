package PracticeLambdaExpression;

public class ClientHelloWorld {

	public static void main(String[] args) {
		
		// ALL THREE IMPLEMENTATION ARE DOING THE SAME THINGS
		
		// by creating instance of a class
		iHellowWorld helloworld = new HelloWorld();
		//helloworld.hello();    // **** now you dont need this statement;
		printHelloByLambda(helloworld);
		
		
		// by creating inline anonymous class of a interface iHellowWorld
		iHellowWorld inlineFunc = new iHellowWorld() {
			@Override
			public void hello() {
				System.out.println("Hello World");
			}
		};
		//inlineFunc.hello(); // **** now you dont need this statement;
		printHelloByLambda(inlineFunc);
		
		
		
		// here we also implementing the same thing that means we are actually implementing the interface
		iHellowWorld helloLambda = () -> System.out.println("Hello World");
		//helloLambda.hello(); // **** now you dont need this statement;

		printHelloByLambda(helloLambda);
		
		
	}

	private static void printHelloByLambda(iHellowWorld helloLambda) {
		helloLambda.hello();		
	}

}

package Chaptered_PracticeLambdaExpression;

public class Thread_Runnable_Example {

	public static void main(String[] args) {
		
		// Solution #01
		Thread myThread = new Thread( new Runnable() {

			@Override
			public void run() {
				System.out.println("Hello World");
			}
		});
		myThread.run();
		
		// Solution #02
		Thread myLambda = new Thread(() -> System.out.println("Hello World printing by run() method"));
		myLambda.run();

	}

}

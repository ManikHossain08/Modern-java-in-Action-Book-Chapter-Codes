package PracticeLambdaExpression;

public class Thread_Runnable_Example {

	public static void main(String[] args) {
		Thread myThread = new Thread( new Runnable() {

			@Override
			public void run() {
				System.out.println("Hello World");
			}
		});
		myThread.run();
		
		Thread myLambda = new Thread(() -> System.out.println("Hello World printing by run() method"));
		myLambda.run();

	}

}

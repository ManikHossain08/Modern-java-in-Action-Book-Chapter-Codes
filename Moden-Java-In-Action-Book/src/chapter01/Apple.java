package chapter01;

public class Apple {

	private int weight = 0;
	private String color = "";

	public Apple(int weight, String color) {
		this.weight = weight;
		this.color = color;
	}
	
	public Apple() {
		this.weight = 100;
		this.color = "RED";
	}
	
	public Apple(int w) {
		this.weight = w;
		this.color = "RED";
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return String.format("Apple{color='%s', weight=%d}", color, weight);
	}
	
	public static boolean isGreen(Apple a) {
		return a.getColor().contentEquals("GREEN");
	}

}
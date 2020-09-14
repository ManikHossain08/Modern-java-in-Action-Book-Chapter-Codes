package Chapter02;

public class MeaningOfThis {

  public final int value = 4;

  public void doIt() {
    @SuppressWarnings("unused")
	int value = 6;
    Runnable r = new Runnable() {
      public final int value = 5;
      @Override
      public void run() {
        @SuppressWarnings("unused")
		int value = 10;
        System.out.println(this.value);
      }
    };
    r.run();
  }

  public static void main(String... args) {
    MeaningOfThis m = new MeaningOfThis();
    m.doIt(); // ???
    
  }

}

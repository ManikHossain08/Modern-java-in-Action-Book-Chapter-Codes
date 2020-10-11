public class SingleObject {

   /** 
    * create an object of SingleObject embedded as a static member of the class itself
    */
   private static SingleObject instance = new SingleObject();

   /** 
    * Make the constructor private so that this class cannot be instantiated
    */
   private SingleObject(){}

   /**
    * If the instance was not previously created, create it. Then return the instance
    */
   public static SingleObject getInstance(){
      if (instance == null)
    	  instance = new SingleObject();
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");
   }
}
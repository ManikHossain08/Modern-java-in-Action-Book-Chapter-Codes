package BuilderDesignPattern;

public class Client_BankAccount {
	public static void main(String[] args) {
		BankAccount account = new BankAccount.Builder(1000)
							  .atBranch("Uttara")
							  .withOwner("Manik")
							  .openningBalance(3000)
							  .atRate(2.5)
							  .Build();
		System.out.println(account);
		
		BankAccount account1 = new BankAccount.Builder(2000)
							  .atBranch("Nikunjo")
							  .withOwner("Hossain")
							  .Build();
		System.out.println(account1);
	}
}

package BuilderDesignPattern;


// MUST READ TO UNDERSTAND PROPERLY: https://dzone.com/articles/design-patterns-the-builder-pattern

public class BankAccount {
	private int accountNo;
	private String owner;
	private String branch;
	private double balance;
	private double interestRate;
	

	@Override
	public String toString() {
		return "BankAccount [accountNo=" + accountNo + ", owner=" + owner + ", branch=" + branch + ", balance="
				+ balance + ", interestRate=" + interestRate + "]";
	}


	public static class Builder{
		private int accountNo;
		private String owner;
		private String branch;
		private double balance;
		private double interestRate;
	
		public Builder (int accountNo) {
			this.accountNo = accountNo;
		}
		
		public Builder withOwner(String owner) {
			this.owner = owner;
			return this;
		}
		
		public Builder atBranch(String branch) {
			this.branch = branch;
			return this;
		}
		
		public Builder openningBalance(double balance) {
			this.balance = balance;
			return this;
		}
		
		public Builder atRate(double rate) {
			this.interestRate = rate;
			return this;
		}
		
		public BankAccount Build() {
			
			BankAccount account = new BankAccount();
			account.accountNo = this.accountNo;
			account.owner = this.owner;
			account.branch = this.branch;
			account.balance = this.balance;
			account.interestRate = this.interestRate;
			
			return account;
			
		}

	}
	
	
	private BankAccount() {
		// constructor is now private
	}
}


public class BankAccount {

	private String ID;
	private double balance;
	private AccountType type;

	public BankAccount(String ID, double balance, AccountType type) {
		this.ID = ID;
		this.balance = balance;
		this.type = type;
	}

	public double getBalance() {
		return this.balance;
	}

	public String getID() {
		return this.ID;
	}

	public AccountType getType() {
		return this.type;
	}
}

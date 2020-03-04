
import java.util.Random;
import java.util.Scanner;

public class BankApplication {

	private Driver database;

	public BankApplication(String password) {
		this.database = new Driver("BankApplication", "root", password);
	}

	public void addCustomer(Customer customer) {
		this.database.InsertCustomer(customer.getID(), customer.getName(), customer.getDiscountFactor());
	}

	public void addAccount(BankAccount account) {

		if (this.database.Exists("Customer", account.getID()) == false) {
			System.out.println("Please Create a Customer Profile");
		} else {
			if (this.database.Exists("Account", account.getID()) == false) {
				this.database.InsertAccount(account.getID(), account.getBalance(), account.getType().toString());
			} else {
				if (this.database.CountAccount(account.getID()) == 2) {
					System.out.println("Only 2 Accounts Allowed (Checkings & Savings)");
				}

				if (this.database.CountAccount(account.getID()) == 1
						&& this.database.CountAccountType(account.getID(), "Checking") == 1) {
					if (account.getType() == AccountType.Checking) {
						System.out.println("Two Checkings not Allowed");
					} else {
						this.database.InsertAccount(account.getID(), account.getBalance(),
								account.getType().toString());
					}
				}

				if (this.database.CountAccount(account.getID()) == 1
						&& this.database.CountAccountType(account.getID(), "Savings") == 1) {
					if (account.getType() == AccountType.Savings) {
						System.out.println("Two Savings not Allowed");
					} else {
						this.database.InsertAccount(account.getID(), account.getBalance(),
								account.getType().toString());
					}
				}
			}
		}

	}

	public String generateID() {
		Random random = new Random();
		String ID = "";
		int randomInt;
		random.nextInt(10);

		for (int i = 0; i < 8; i++) {
			randomInt = random.nextInt(10);
			ID = ID + Integer.toString(randomInt);
		}

		return ID;
	}

	public void createCustomer(String name, double discount_factor) {
		int y = 0;
		String ID;
		while (y == 0) {
			ID = generateID();

			if (this.database.Exists("Customer", ID) == false) {
				this.database.InsertCustomer(ID, name, discount_factor);
				System.out.println("Your ID is " + ID);
				break;
			}
		}
	}

	public void getBalance(String ID, AccountType type) {
		this.database.getBalance(ID, type.toString());
	}

	public void deposit(String ID, double amount, AccountType type) {
		this.database.deposit(ID, amount, type.toString());
	}

	public void withdraw(String ID, double amount, AccountType type) {
		this.database.withdrawal(ID, amount, type.toString());
	}

	public void transfer(String ID, double amount, AccountType from, AccountType to) {
		this.database.withdrawal(ID, amount, from.toString());
		this.database.deposit(ID, amount, to.toString());
	}

	public void GUI() {
		MainFrame gui = new MainFrame(this);

	}

	public static void main(String[] args) {
		BankApplication application = new BankApplication("aymen621517%");
		application.GUI();
	}
}

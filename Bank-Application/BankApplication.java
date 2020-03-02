
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
					this.database.InsertAccount(account.getID(), account.getBalance(), account.getType().toString());
				}
			}

			if (this.database.CountAccount(account.getID()) == 1
					&& this.database.CountAccountType(account.getID(), "Savings") == 1) {
				if (account.getType() == AccountType.Savings) {
					System.out.println("Two Savings not Allowed");
				} else {
					this.database.InsertAccount(account.getID(), account.getBalance(), account.getType().toString());
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

	public void UI() {
		Scanner scan = null;
		int x = 0;
		int y = 0;

		while (x == 0) {
			scan = new Scanner(System.in);
			String input;
			String ID;
			double discount_factor;
			double amount;
			String name;
			AccountType type = null;
			double balance;

			System.out.println("Welcome to Bank App, what would you like to do?");
			System.out.println("(a) Create Customer");
			System.out.println("(b) Create Bank Account");
			System.out.println("(c) Get balance");
			System.out.println("(d) Deposit");
			System.out.println("(e) Withdrawal");
			System.out.println("(f) Make a transfer");
			System.out.println("(g) Quit");

			input = scan.nextLine();
			System.out.println(input);

			if (input.equals("a")) {
				System.out.println("Please Enter Name");
				name = scan.nextLine();

				System.out.println("Please Enter Discount Factor");
				discount_factor = scan.nextDouble();

				while (y == 0) {
					ID = generateID();

					if (this.database.Exists("Customer", ID) == false) {
						this.database.InsertCustomer(ID, name, discount_factor);
						System.out.println("Your ID is " + ID);
						break;
					}
				}
			} else if (input.equals("b")) {
				System.out.println("Please Enter ID");
				ID = scan.nextLine();

				if (database.Exists("Customer", ID) == true) {
					System.out.println("Please Enter Account Type");
					System.out.println("(a) Checkings");
					System.out.println("(b) Savings");
					input = scan.nextLine();
					System.out.println("Enter Balance");
					balance = scan.nextDouble();

					if (input.equals("a")) {
						type = AccountType.Checking;
					}
					if (input.equals("b")) {
						type = AccountType.Savings;
					}

					addAccount(new BankAccount(ID, balance, type));
				} else {
					System.out.println("ID Not Valid, Please Make Customer Profile");
				}

			} else if (input.equals("c")) {
				System.out.println("Please Enter ID");
				ID = scan.nextLine();

				System.out.println("Please Enter Account Type");
				System.out.println("(a) Checkings");
				System.out.println("(b) Savings");

				input = scan.nextLine();

				if (input.equals("a")) {
					type = AccountType.Checking;
					this.database.getBalance(ID, type.toString());

				}
				if (input.equals("b")) {
					type = AccountType.Savings;
					this.database.getBalance(ID, type.toString());
				}
			} else if (input.equals("d")) {
				System.out.println("Please Enter ID");
				ID = scan.nextLine();

				System.out.println("Please Enter Account Type");
				System.out.println("(a) Checkings");
				System.out.println("(b) Savings");

				input = scan.nextLine();
				if (input.equals("a")) {
					type = AccountType.Checking;
				}
				if (input.equals("b")) {
					type = AccountType.Savings;
				}

				System.out.println("Please Enter Deposit Amount");

				amount = scan.nextDouble();

				this.database.deposit(ID, amount, type.toString());
			} else if (input.equals("d")) {
				System.out.println("Please Enter ID");
				ID = scan.nextLine();

				System.out.println("Please Enter Account Type");
				System.out.println("(a) Checkings");
				System.out.println("(b) Savings");

				input = scan.nextLine();

				System.out.println("Please Enter Withdrawal Amount");

				amount = scan.nextDouble();
				if (input.equals("a")) {
					type = AccountType.Checking;
					this.database.withdrawal(ID, amount, type.toString());
				}
				if (input.equals("b")) {
					type = AccountType.Savings;
					if (amount > 1000) {
						this.database.withdrawal(ID, amount, type.toString());
					}
				}

			} else if (input.equals("f")) {
				AccountType from = null;
				AccountType to = null;
				System.out.println("Please Enter ID");
				ID = scan.nextLine();

				System.out.println("Please Enter Transfer From To");
				System.out.println("(a) Checkings->Savings");
				System.out.println("(b) Savings->Checkings");

				input = scan.nextLine();

				System.out.println("Please Enter Transfer Amount");

				amount = scan.nextDouble();

				if (input.equals("a")) {
					from = AccountType.Checking;
					to = AccountType.Savings;

					this.database.deposit(ID, amount, to.toString());
					this.database.withdrawal(ID, amount, from.toString());

				}

				if (input.equals("b")) {
					from = AccountType.Savings;
					to = AccountType.Checking;

					this.database.deposit(ID, amount, to.toString());
					this.database.withdrawal(ID, amount, from.toString());

				}
			} else if (input.equals("g")) {
				x = 1;
				break;
			} else {
				System.out.println("Please Enter Valid Option");
			}
		}

		scan.close();
	}

	public static void main(String[] args) {
		BankApplication application = new BankApplication("*******");
		application.UI();
	}
}

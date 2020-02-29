
public class BankAccount {
	
	
	private Customer customer;
	private double balance;
	private AccountType type;
	
	public BankAccount(Customer customer, double balance, AccountType type)
	{
		this.customer=customer;
		this.balance=balance;
		this.type=type;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
	
	public Customer getCustomer()
	{
		return this.customer;
	}
	
	public AccountType getType()
	{
		return this.type;
	}
	
	
	public void deposit(double amount)
	{
		this.balance=this.balance+amount;
		
		
	}
	
	public void withdrawal(double amount)
	{
		if (this.type==AccountType.savings)
		{
			if(amount>1000)
			{
				this.balance=this.balance-amount;
			}
			else
			{
				System.out.println("Savings Withrawal Must be more than 1000");
			}
		}
		
		
	}
	
	private void transfer(double amount)
	{
		this.balance=this.balance+amount;
	}
	
	public void transfer(BankAccount account,double amount)
	{
		if(account.getCustomer()==account.getCustomer())
		{
			this.balance=this.balance-amount;
			account.transfer(amount);
		}
		else
		{
			System.out.println("Transfer Not Allowed");
		}
		
	}
	
	

}

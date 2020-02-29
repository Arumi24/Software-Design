
import java.util.HashMap; 
import java.util.Map; 
import java.util.Random;
import java.util.*;
import java.util.Scanner;


public class BankApplication {
	
	private HashMap<String, Customer> list;
	private HashMap<Customer, List<BankAccount>> map; 
	
	public BankApplication(HashMap<Customer, List<BankAccount>> map,HashMap<String, Customer> list)
	{
		
		this.map=map;
		this.list=list;
	}
	
	public void addCustomer(Customer customer)
	{
		this.list.put(customer.getID(),customer);
	}
	
	public void addAccount(BankAccount account)
	{
		if(this.map.containsKey(account.getCustomer())==true)
		{
			if(this.map.get(account.getCustomer()).size()==2)
			{
				System.out.println("Max 2 Accounts Allowed(Savings & Checking)");
			}
			else if(this.map.get(account.getCustomer()).size()==1 && 
					this.map.get(account.getCustomer()).get(0).getType()==AccountType.checkings)
			{
				if(account.getType()==AccountType.checkings)
				{
					System.out.println("Two Checkings Not Allowed");
				}
				else
				{
					this.map.get(account.getCustomer()).add(account);	
				}
			}
			else
			{
				if(account.getType()==AccountType.savings)
				{
					System.out.println("Two Savings Not Allowed");
				}
				else
				{
					this.map.get(account.getCustomer()).add(account);	
				}
			}			
		}
		else
		{		
			this.map.put(account.getCustomer(), new LinkedList<BankAccount>());	
			this.map.get(account.getCustomer()).add(account);
		}
	}
	
	public String generateID()
	{
		Random random = new Random();
		String ID="";
		int randomInt; random.nextInt(10);
		
		for(int i=0;i<8;i++)
		{
			randomInt= random.nextInt(10);	
			ID=ID+Integer.toString(randomInt);
		}
		
		return ID;
	}
	
	public void UI()
	{
		int x=0;
		int y=0;
		
		while(x==0)
		{
			Scanner scan = new Scanner(System.in);
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
			
			input=scan.nextLine();
			System.out.println(input);
			if(input.equals("a"))
			{
				System.out.println("Please Enter Name");
				name=scan.nextLine();
				
				System.out.println("Please Enter Discount Factor");
				discount_factor=scan.nextDouble();
				
				while(y==0)
				{
					ID=generateID();
									
					if(this.list.containsKey(ID)==false)
					{	
						this.list.put(ID, new Customer(ID,name,discount_factor));	
						System.out.println("Your ID is "+ID);
						break;
					}
				
				}		
				
			}
			else if(input.equals("b"))
			{
				
				System.out.println("Please Enter ID");
				ID=scan.nextLine();

				if(this.list.containsKey(ID)==true)
				{
					System.out.println("Please Enter Account Type");
					System.out.println("(a) Checkings");
					System.out.println("(b) Savings");
					input=scan.nextLine();
					System.out.println("Enter Balance");
					balance= scan.nextDouble();
				
					if(input.equals("a"))
					{
						type=AccountType.checkings;
					}
					if(input.equals("b"))
					{
						type=AccountType.savings;
					}
					
					addAccount(new BankAccount(this.list.get(ID),balance,type));
					
				}
				else
				{
					System.out.println("ID Not Valid, Please Make Customer Profile");
				}
				
			}
			else if(input.equals("c"))
			{
				System.out.println("Please Enter ID");
				ID=scan.nextLine();
				
				System.out.println("Please Enter Account Type");
				System.out.println("(a) Checkings");
				System.out.println("(b) Savings");

				input=scan.nextLine();
				if(input.equals("a"))
				{
					type=AccountType.checkings;
					
					for(int i=0;i<this.map.get(this.list.get(ID)).size();i++)
					{
						if(this.map.get(this.list.get(ID)).get(i).getType()==type)
						{
							System.out.println(this.map.get(
								this.list.get(ID)).get(i).getBalance());
						}
					}
					
				}
				if(input.equals("b"))
				{
					type=AccountType.savings;
					
					for(int i=0;i<this.map.get(this.list.get(ID)).size();i++)
					{
						if(this.map.get(this.list.get(ID)).get(i).getType()==type)
						{
							System.out.println(
								this.map.get(this.list.get(ID)).get(i).getBalance());
						}
					}
				}
				
				
			}
			else if(input.equals("d"))
			{
				System.out.println("Please Enter ID");
				ID=scan.nextLine();
				
				System.out.println("Please Enter Account Type");
				System.out.println("(a) Checkings");
				System.out.println("(b) Savings");
				
				input=scan.nextLine();
				if(input.equals("a"))
				{
					type=AccountType.checkings;
				}
				if(input.equals("b"))
				{
					type=AccountType.savings;
				}
				
				System.out.println("Please Enter Deposit Amount");
				
				amount= scan.nextDouble();
							

				for(int i=0;i<this.map.get(this.list.get(ID)).size();i++)
				{
					if(this.map.get(this.list.get(ID)).get(i).getType()==type)
					{
						this.map.get(this.list.get(ID)).get(i).deposit(amount);
					}
				}
			}
			else if(input.equals("e"))
			{
				System.out.println("Please Enter ID");
				ID=scan.nextLine();
				
				System.out.println("Please Enter Account Type");
				System.out.println("(a) Checkings");
				System.out.println("(b) Savings");
				
				input=scan.nextLine();
				if(input.equals("a"))
				{
					type=AccountType.checkings;
				}
				if(input.equals("b"))
				{
					type=AccountType.savings;
				}
				
				System.out.println("Please Enter Withdrawal Amount");
				
				amount=scan.nextDouble();
							
				
				for(int i=0;i<this.map.get(this.list.get(ID)).size();i++)
				{
					if(this.map.get(this.list.get(ID)).get(i).getType()==type)
					{
						this.map.get(this.list.get(ID)).get(i).withdrawal(amount);
					}
				}
			}
			else if(input.equals("f"))
			{
				System.out.println("Please Enter ID");
				ID=scan.nextLine();
				
				System.out.println("Please Enter Transfer From To");
				System.out.println("(a) Checkings->Savings");
				System.out.println("(b) Savings->Checkings");
				
				input=scan.nextLine();
				
				System.out.println("Please Enter Transfer Amount");
				
				amount=scan.nextDouble();
				
				if(input.equals("a"))
				{
					type=AccountType.checkings;
					
					for(int i=0;i<this.map.get(this.list.get(ID)).size();i++)
					{
						if(this.map.get(this.list.get(ID)).get(i).getType()==type && i==0)
						{
							this.map.get(this.list.get(ID)).get(i).transfer(
								this.map.get(this.list.get(ID)).get(1),amount);
						}
						if(this.map.get(this.list.get(ID)).get(i).getType()==type && i==1)
						{
							this.map.get(this.list.get(ID)).get(i).transfer(
								this.map.get(this.list.get(ID)).get(0),amount);
						}
					}	
				}
				if(input.equals("b"))
				{
					type=AccountType.savings;
					
					for(int i=0;i<this.map.get(this.list.get(ID)).size();i++)
					{
						if(this.map.get(this.list.get(ID)).get(i).getType()==type && i==0)
						{
							this.map.get(this.list.get(ID)).get(i).transfer(
								this.map.get(this.list.get(ID)).get(1),amount);
						}
						if(this.map.get(this.list.get(ID)).get(i).getType()==type && i==1)
						{
							this.map.get(this.list.get(ID)).get(i).transfer(
								this.map.get(this.list.get(ID)).get(0),amount);
						}
					}
				}		
			}
			else if(input.equals("g"))
			{
				x=1;
				break;
			}
			else
			{
				System.out.println("Please Enter Valid Option");
			}				
		}		
	}
	
	public static void main(String[] args)
	{
		HashMap<String, Customer> list= new HashMap<String, Customer>();
		HashMap<Customer, List<BankAccount>> map= new HashMap<Customer, List<BankAccount>>();
				
		BankApplication application = new BankApplication(map,list);
		
		application.UI();
						
	}
}

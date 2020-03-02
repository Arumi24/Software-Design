import java.sql.*;

public class Driver {
	
	private Connection myConn;
	private Statement myStmt;

	public Driver(String database,String user, String password)
	{
		String url="jdbc:mysql://localhost:3306/"+database+"?autoReconnect=true&useSSL=false";
		
		try
		{		
			this.myConn = DriverManager.getConnection(url,user,password);							
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	
	public void InsertCustomer(String ID, String Name,double DiscountFactor) 
	{
		try
		{		
			this.myStmt=this.myConn.createStatement();
			String sql= "INSERT INTO Customer VALUES ('"+ID+"','"+Name+"',"+DiscountFactor+");";
			System.out.println(sql);
			myStmt.executeUpdate(sql);
									
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void InsertAccount(String ID,double balance, String type) 
	{
		try
		{		
			this.myStmt=this.myConn.createStatement();
			String sql= "INSERT INTO Account VALUES ('"+ID+"',"+balance+",'"+type+"');";
			System.out.println(sql);
			myStmt.executeUpdate(sql);
									
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	public int Count(String Table) 
	{
		int count = 0;
		try
		{
			this.myStmt=this.myConn.createStatement();
			String sql="select count(*) as count from "+Table+";";
			ResultSet rs= myStmt.executeQuery(sql);
			
			while(rs.next())
			{
				count=(rs.getInt("count"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public int CountAccount(String ID) 
	{
		int count = 0;
		try
		{
			this.myStmt=this.myConn.createStatement();
			String sql="select count(*) as count from Account where ID="+ID;
			ResultSet rs= myStmt.executeQuery(sql);
			
			while(rs.next())
			{
				count=(rs.getInt("count"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public int CountAccountType(String ID,String type) 
	{
		int count = 0;
		try
		{
			this.myStmt=this.myConn.createStatement();
			String sql="select count(*) as count from Account where ID="+ID+" and Type='"+type+"';";
			ResultSet rs= myStmt.executeQuery(sql);
			
			while(rs.next())
			{
				count=(rs.getInt("count"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public void getBalance(String ID,String type)
	{
		try
		{
			this.myStmt=this.myConn.createStatement();
			String sql="select * from Account where ID="+ID+" and Type='"+type+"';";
			ResultSet rs= myStmt.executeQuery(sql);
			
			while(rs.next())
			{
				System.out.println(rs.getInt("Balance"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean Exists(String table,String ID)
	{
		int count = 0;
		try
		{
			this.myStmt=this.myConn.createStatement();
			String sql="select count(*) as count from "+ table +" where ID='"+ID+"';";
			ResultSet rs= myStmt.executeQuery(sql);
			
			while(rs.next())
			{
				count=(rs.getInt("count"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}    
	
	   if (count==0)
	   {
		   return (false);
	   }
	   else
	   {
		   return (true);
	   }
	}
	
	public void printAccounts()
	{
		try
		{
			this.myStmt=this.myConn.createStatement();
			String sql="select * from Account;";
			ResultSet rs= myStmt.executeQuery(sql);
			
			while(rs.next())
			{
				System.out.println(rs.getInt("ID")+" "+ rs.getString("Balance")+" "+rs.getString("Type"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}    
	}
	
	public void printCustomers()
	{

		try
		{
			this.myStmt=this.myConn.createStatement();
			String sql="select * from Customers;";
			ResultSet rs= myStmt.executeQuery(sql);
			
			while(rs.next())
			{
				System.out.println(rs.getInt("ID")+" "+ rs.getString("FirstName")+
						" "+rs.getString("LastName")+rs.getString("DiscountFactor"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}    
	   
	}
	
	public void deposit(String ID,double amount, String type)
	{
		try
		{		
			this.myStmt=this.myConn.createStatement();
			String sql= "update Account set Balance=Balance+"+amount+" where ID="+ID+" and Type='"+type+"';";
			System.out.println(sql);
			myStmt.executeUpdate(sql);
									
		}
			
		
		
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void withdrawal(String ID,double amount, String type)
	{
		try
		{		
			this.myStmt=this.myConn.createStatement();
			String sql= "update Account set Balance=Balance-"+amount+" where ID="+ID+" and Type='"+type+"';";
			System.out.println(sql);
			myStmt.executeUpdate(sql);
									
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}

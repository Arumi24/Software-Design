
public class Customer {
	
	private String name;
	private String ID;
	private double discount_percentage;

	public Customer(String name,String ID,double discount_percentage)
	{
		this.name=name;
		this.ID=ID;
		this.discount_percentage=discount_percentage;	
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getID()
	{
		return this.ID;
	}
	
	public double getDiscountFactor()
	{
		return this.discount_percentage;
	}	
}

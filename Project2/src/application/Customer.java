package application;

import java.util.ArrayList;
public class Customer implements Comparable <Customer>
{
	private String name;
	private String address;
	private String plan;
	private String ID;
	private String MobileNum;
	private ArrayList <String> interestedIn = new ArrayList<>(); 
	private ArrayList <String> rented = new ArrayList<>();
	
	public Customer()
	{
		this.name = "";
		this.address = "";
		this.plan = "";
		this.ID = "";
		this.MobileNum = "";
	}
	public Customer(String ID ,String name, String address,String MobileNum ,String plan)
	{
		this.ID = ID;
		this.name = name;
		this.address = address;
		this.MobileNum = MobileNum;
		this.plan = plan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalArgumentException{
		if((name.contains("0")) || (name.contains("1")) || (name.contains("2")) || (name.contains("3")) || (name.contains("4")) || 
			(name.contains("5")) || (name.contains("6")) || (name.contains("7")) || (name.contains("8")) || (name.contains("9")))
		{
			throw new IllegalArgumentException("Name must not have numbers");
		}
		else
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) throws IllegalArgumentException{
		if(plan.equalsIgnoreCase("limited") || plan.equalsIgnoreCase("unlimited"))
		{
			this.plan = plan;
		}
		else
			throw new IllegalArgumentException("You have to enter only (LIMITED or UNLIMITED).");
		
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getMobileNum() {
		return MobileNum;
	}
	public void setMobileNum(String mobileNum) {
		MobileNum = mobileNum;
	}
	public ArrayList<String> getInterestedIn() {
		return interestedIn;
	}
	public ArrayList<String> getRented() {
		return rented;
	}
	public int compareTo(Customer c)
	{
		if(getName().compareToIgnoreCase(this.getName())>0)
		{
			return 1;
		}
		else if(getName().compareToIgnoreCase(this.getName())<0)
		{
			return -1;
		}
		else
			return 0;
	}
	@Override
	public String toString() {
		return  ID + "$" + name + "$" + address + "$" + MobileNum + "$" + plan;
	}
	
	
	
	
}

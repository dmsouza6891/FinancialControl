package model;

//Represents a financial repository

public class Account {
	
	private String name;    //name that identifies the financial repository
	private double balance; //stored amount in the repository
	
	public Account(String name, double balance) { //Constructor with two parameters.
		this.name = name;
		setBalance(balance);
	}
	
	public void setBalance(double balance) { //Configure the balance. In case of negative value it assigns zero value to the variable 
		if(balance >= 0.0)
			this.balance = balance;
		else
			this.balance = 0.0;
	}
	
	public String getName() { 
		return this.name;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	@Override
	public String toString() {
		return String.format("Account Name:%s Balance:%.2f", this.name, this.balance);
	}

}

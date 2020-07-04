package model;

//Represents an account-type financial repository 

public class BankAccount extends FinancialRepository{
	
	private double overdraftAvailable;  //Overdraft amount available to account
	private double overdraftUsed;  		//Overdraft amount used 
	
	public BankAccount(String name, double balance, double overdraftAvailable) { //Constructor with three parameters.
		super(name, balance);
		setOverdraftAvailable(overdraftAvailable);
		if(balance < 0.0)
			setOverdraftUsed(balance);
		else
			overdraftUsed = 0.0;
	}
	
	public void setOverdraftUsed(double value) { //Adds value to used overdraft
		if(value < getCurrentOverdraft())
			this.overdraftUsed += value;
	} 
	
	public void setOverdraftAvailable(double overdraftAvailable){//Configure available overdraft. In case of negative value it assigns zero value to the variable 
		if(overdraftAvailable >= 0.0)
			this.overdraftAvailable = overdraftAvailable;
		else
			this.overdraftAvailable = 0.0;
	}
	
	@Override
	public double getBalance() { //Returns the adjusted balance of account considering the use of overdraft.  
		if(overdraftUsed > 0.0) 
			return -overdraftUsed;
		else
			return super.getBalance();
	}
	
	public double getCurrentOverdraft() { //Returns the current available overdraft
		return overdraftAvailable - overdraftUsed;
	}
	
	public double getOverdraftAvailable() { //Returns the overdraft available
		return this.overdraftAvailable;
	}
	
	@Override
	public String toString() {
		super.toString();
		return String.format("Available Overdraft: %.2f Used Overdraft: %.2f Balance With Overdraft:%.02f", this.overdraftAvailable, 
				                                                                                            this.overdraftUsed, getBalance());
	}
	
}//end class

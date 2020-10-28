package model;

//Represents an bank account

public class BankAccount extends Account{
	
	private double overdraftAvailable;  //Overdraft amount available to account
	private double overdraftUsed;  		//Overdraft amount used 
	private String bank;	            //Name of bank
	private String agencyNumber; 		//Bank Agency Number
	private String accountNumber;       //Bank Account Number 
	
	public BankAccount(int id, String name, double balance, double overdraftAvailable, String bank, String agencyNumber, String accountNumber) { //Full Constructor.
		super(id, name, balance);
		
		this.bank = bank;
		this.agencyNumber = agencyNumber;
		this.accountNumber = accountNumber;
		
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
	
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAgencyNumber() {
		return agencyNumber;
	}

	public void setAgencyNumber(String agencyNumber) {
		this.agencyNumber = agencyNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		String superAccount = super.toString();
		return String.format("%s%nAvailable Overdraft: %.2f%nUsed Overdraft: %.2f%nBalance With Overdraft:%.02f",superAccount, 
																											     this.overdraftAvailable, 
				                                                                                                 this.overdraftUsed, getBalance());
	}
	
}//end class

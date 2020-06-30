package model;

public class BankAccount extends FinancialRepository{
	
	private double revolvingCredit; 
	private double usedCredit;
	private double creditBalance;
	private double adjustBalance;
	
	public BankAccount(String name, double balance, double revolvingCredit) {
		super(name, balance);

	}
	
	public double adjustBalance(double balance) {
		if(balance >= 0.0) 
			return balance; 
		else
			return 0.0;
	}
	
	public void setRevolvingCredit(double revolvingCredit) {
		if(revolvingCredit >= 0.0)
			this.revolvingCredit = revolvingCredit;
		else
			this.revolvingCredit = 0.0;
	}
	
	public double revolvingCredit() {
		return this.revolvingCredit;
	}
	
	

}

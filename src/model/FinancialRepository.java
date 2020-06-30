package model;

public class FinancialRepository {
	
	private String name;   
	private double balance;
	
	public FinancialRepository(String name, double balance) {
		this.name = name;
		setBalance(balance);
	}
	
	public void setBalance(double balance) {
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
		return String.format("RepositoryName:%s Balance:%.2f", this.name, this.balance);
	}

}

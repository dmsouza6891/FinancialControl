package model;

import java.io.Serializable;

//Represents a financial repository

public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;    //name that identifies the financial repository
	private double balance; //stored amount in the repository
	
	public Account(int id, String name, double balance) { //Constructor with two parameters.
		this.id = id;
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
	
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return String.format("Nome da Conta: %s%nSaldo:%.2f", this.name, this.balance);
	}

}

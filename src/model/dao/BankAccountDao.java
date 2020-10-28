package model.dao;

import com.example.appfinancialcontrol.AddAccountActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;
import db.DatabaseConnector;

public class BankAccountDao {
	
	private DatabaseConnector connection;
	
	public BankAccountDao(DatabaseConnector connection) {
		this.connection = connection;
	}
	
	public void insert(String name, Double balance, Double overdraft, String bank, String agency, String accountNumber) {
		
		ContentValues newAccount = new ContentValues(); 
		newAccount.put("name", name);
		newAccount.put("balance", balance);
		newAccount.put("overdraft", overdraft);
		newAccount.put("bank", bank);
		newAccount.put("agency", agency);
		newAccount.put("accountNumber", accountNumber);
		
		connection.open();
		connection.getDatabase().insert("bank_accounts", null, newAccount);
		connection.close();
		
	}//end insert()
	
	public void update(long id, String name, Double overdraft, String bank, String agency, String accountNumber) {
		
		ContentValues editAccount = new ContentValues();
		editAccount.put("name", name);
		editAccount.put("overdraft", overdraft);
		editAccount.put("bank", bank);
		editAccount.put("agency", agency);
		editAccount.put("accountNumber", accountNumber);
		
		connection.open();
		connection.getDatabase().update("bank_accounts", editAccount, "id=" + id, null);
		connection.close();
	}
	
	public void deleteById(long id) {
		connection.open();
		connection.getDatabase().delete("bank_accounts", "id=" + id, null);
		connection.close();
	}
	
	public Cursor getAll() {
		
		return connection.getDatabase().query("bank_accounts", new String[] {"id", "name", "balance", "overdraft", "bank", "agency", "accountNumber"}, null, null, null, null, "id");
		
	}//end getAll()

}

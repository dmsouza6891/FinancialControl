package model.dao;

import com.example.appfinancialcontrol.AddAccountActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;
import db.DatabaseConnector;

public class AccountDao {
	
	private DatabaseConnector connection;
	
	public AccountDao(DatabaseConnector connection) {
		this.connection = connection;
	}
	
	public void insert(String name, Double balance) {
		
		ContentValues newAccount = new ContentValues(); 
		newAccount.put("name", name);
		newAccount.put("balance", balance);
		
		connection.open();
		connection.getDatabase().insert("accounts", null, newAccount);
		connection.close();
		
	}//end insert()
	
	public void updateName(long id, String name) {
		
		ContentValues editAccount = new ContentValues();
		editAccount.put("name", name);
		
		connection.open();
		connection.getDatabase().update("accounts", editAccount, "id=" + id, null);
		connection.close();
	}
	
	public void deleteById(long id) {
		connection.open();
		connection.getDatabase().delete("accounts", "id=" + id, null);
		connection.close();
	}
	
	public Cursor getAll() {
		
		return connection.getDatabase().query("accounts", new String[] {"id", "name", "balance"}, null, null, null, null, "id");
		
	}//end getAll()

}

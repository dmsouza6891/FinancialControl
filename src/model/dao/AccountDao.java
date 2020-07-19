package model.dao;

import com.example.appfinancialcontrol.AddAccount;

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
	
	public Cursor getAll() {
		
		Cursor result;
		
		connection.open();
		result = connection.getDatabase().query("accounts", new String[] {"id", "name", "balance"}, null, null, null, null, "id");
		
		
		return result;
		
	}//end getAll()

}

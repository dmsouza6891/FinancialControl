package db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnector {
	
	private static final String DATABASE_NAME = "database"; //name of database
	private SQLiteDatabase database; //database object
	private DatabaseOpenHelper databaseOpenHelper; //helper of database
	
	public DatabaseConnector(Context context) {
		
		databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
		
	}
	
	public void open() throws SQLException{
		
		database = databaseOpenHelper.getWritableDatabase();
		
	}
	
	public void close() {
		
		if(database != null) {
			database.close();
		}
		
	}
	
	public SQLiteDatabase getDatabase() {
		
		return database;
		
	}
	
	/*private class DatabaseOpenHelper extends SQLiteOpenHelper{
		
		public DatabaseOpenHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			String createQuery = "CREATE TABLE accounts" +
								 "(id integer primary key autoincrement," +
								 "name TEXT, balance DOUBLE);";
			
			db.execSQL(createQuery);
								 
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	}*/
	
	
	
	/*public void insert(String name, double balance) {
		
		ContentValues newAccount = new ContentValues();
		newAccount.put("name", name);
		newAccount.put("balance", balance);
		
		open();
		database.insert("Accounts", null, newAccount);
	}
	
	public Cursor getOneAccount(long id) {
		return database.query("Accounts", null, "id="+ id, null, null, null, null);
	}*/
	
	
	
}//end of class



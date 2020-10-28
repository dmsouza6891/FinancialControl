package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseOpenHelper extends SQLiteOpenHelper{
		
	public DatabaseOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
		
	@Override
	public void onCreate(SQLiteDatabase db) {
		String createQueryAccount = "CREATE TABLE accounts" +
							 "(id integer primary key autoincrement," +
							 "name TEXT,"+
							 "balance DOUBLE);";
		
		String createQueryBankAccount = "CREATE TABLE bank_accounts" +
				 "(id integer primary key autoincrement," +
				 "name TEXT,"+
				 "balance DOUBLE,"+
				 "overdraft DOUBLE,"+
				 "bank TEXT,"+
				 "agency TEXT,"+
				 "accountNumber TEXT);";
		
		db.execSQL(createQueryAccount);
		db.execSQL(createQueryBankAccount);
							 
	}
		
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
}//end class

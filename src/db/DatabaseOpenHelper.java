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
		String createQuery = "CREATE TABLE accounts" +
							 "(id integer primary key autoincrement," +
							 "name TEXT,"+
							 "balance DOUBLE);";
		
		db.execSQL(createQuery);
							 
	}
		
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
}//end class

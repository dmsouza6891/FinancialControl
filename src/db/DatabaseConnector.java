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
	
}//end of class



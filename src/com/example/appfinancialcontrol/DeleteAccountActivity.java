package com.example.appfinancialcontrol;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import db.DatabaseConnector;
import gui.util.Alert;
import model.Account;
import model.dao.AccountDao;

public class DeleteAccountActivity extends ListActivity {
	
	private ListView accountsList;
	private ArrayList<Account> records;
	private AccountDao accountDao;
	private DatabaseConnector connection;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_accounts);
		
		connection = new DatabaseConnector(this);
		accountDao = new AccountDao(connection);
		
		accountsList = getListView();
		records = new ArrayList<Account>();
		Bundle extras = getIntent().getExtras();
		records = (ArrayList<Account>) extras.getSerializable("row_id");
		ArrayAdapter<Account> adapterListAccount = new ArrayAdapter<Account>(this, R.layout.list_item, records);
		accountsList.setAdapter(adapterListAccount);
		
	}//end onCreate()	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		final Account itemAccount = records.get(position);
		AlertDialog.Builder confirmAlert = new AlertDialog.Builder(this);
		confirmAlert.setTitle(R.string.confirm_title);
		confirmAlert.setMessage(R.string.confirm_message);
		confirmAlert.setPositiveButton(R.string.button_delete, 
									   new DialogInterface.OnClickListener() {
									       @Override
			                               public void onClick(DialogInterface dialog, int button) {
									    	   try {
									    		   accountDao.deleteById(itemAccount.getId()); 
									    		   Alert.showShortAlert(DeleteAccountActivity.this, "Conta \"" + itemAccount.getName() + "\" excluída com sucesso!");
									    	   }
									    	   catch(Exception e) {
									    		   Alert.showShortAlert(DeleteAccountActivity.this, e.getMessage());
									    	   }
			                       	           finish();
			                               }
								       });
		AlertDialog confirmDialog = confirmAlert.create();
		confirmDialog.show();
		
	}//end onListItemClick
	
	@Override
	protected void onStop() {
		
		connection.close();
		super.onStop();
		
	}//end onStop
	
}//end class

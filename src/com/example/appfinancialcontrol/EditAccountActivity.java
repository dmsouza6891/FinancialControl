package com.example.appfinancialcontrol;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import db.DatabaseConnector;
import gui.util.Alert;
import model.Account;
import model.dao.AccountDao;

public class EditAccountActivity extends ListActivity {
	
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
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View accountEdit = inflater.inflate(R.layout.form_new_account, null);
		final EditText name = (EditText) accountEdit.findViewById(R.id.nameAccountEditText);
		final EditText balance = (EditText) accountEdit.findViewById(R.id.balanceAccountEditText);
		
		name.setText(itemAccount.getName());
		name.setImeOptions(EditorInfo.IME_ACTION_DONE);
		balance.setText(String.format("%.02f", itemAccount.getBalance()));
		balance.setEnabled(false);
		
		confirmAlert.setTitle(R.string.update);
		confirmAlert.setView(accountEdit);
		confirmAlert.setPositiveButton(R.string.save, 
									   new DialogInterface.OnClickListener() {
									       @Override
			                               public void onClick(DialogInterface dialog, int button) {
									    	   try {									 
									    		   accountDao.updateName(itemAccount.getId(), name.getText().toString());
									    		   Alert.showShortAlert(EditAccountActivity.this, "Conta editada com sucesso!");
									    	   }
									    	   catch(Exception e) {
									    		   Alert.showShortAlert(EditAccountActivity.this, e.getMessage());
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

package com.example.appfinancialcontrol;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import db.DatabaseConnector;
import gui.util.Alert;
import model.Account;
import model.BankAccount;
import model.TypeAccount;
import model.dao.AccountDao;
import model.dao.BankAccountDao;

public class EditAccountActivity extends ListActivity {
	
	private ListView accountsList;
	private ArrayList<Account> records;
	private AccountDao accountDao;
	private BankAccountDao bankAccountDao;
	private DatabaseConnector connection;

	private TypeAccount typeAccount;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_accounts);
		
		connection = new DatabaseConnector(this);
		accountDao = new AccountDao(connection);
		bankAccountDao = new BankAccountDao(connection);
		
		accountsList = getListView();
		records = new ArrayList<Account>();
		Bundle extras = getIntent().getExtras();
		records = (ArrayList<Account>) extras.getSerializable("row_id");
		ArrayAdapter<Account> adapterListAccount = new ArrayAdapter<Account>(this, R.layout.list_item, records);
		accountsList.setAdapter(adapterListAccount);
		
	}//end onCreate()	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		final Account itemList = records.get(position);
	
		final EditText name;
		final EditText balance;
		final EditText bank;
		final EditText agency;
		final EditText number;
		final EditText overdraft;
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View newFormView = null;
		
		if(records.get(position) instanceof BankAccount){ 
			typeAccount = TypeAccount.BANK_ACCOUNT;
			BankAccount bankAccount = (BankAccount) itemList;
			newFormView = inflater.inflate(R.layout.form_new_bankaccount, null);
			name = (EditText) newFormView.findViewById(R.id.nameAccountEditText);
			name.setText(itemList.getName());
			balance = (EditText) newFormView.findViewById(R.id.balanceAccountEditText);
			balance.setText(String.format("%.02f", itemList.getBalance()));
			overdraft = (EditText) newFormView.findViewById(R.id.overdraftAccountEditText);
			bank = (EditText) newFormView.findViewById(R.id.nameBankAccountEditText);
			agency = (EditText) newFormView.findViewById(R.id.numberAgencyAccountEditText);
			number = (EditText) newFormView.findViewById(R.id.numberAccountEditText);
			overdraft.setText(String.format("%.02f", bankAccount.getOverdraftAvailable()));
			bank.setText(bankAccount.getBank());
			agency.setText(bankAccount.getAgencyNumber());
			number.setText(bankAccount.getAccountNumber());
			balance.setEnabled(false);
		}
		else {
			typeAccount = TypeAccount.ACCOUNT;
		    newFormView = inflater.inflate(R.layout.form_new_account, null);
		    name = (EditText) newFormView.findViewById(R.id.nameAccountEditText);
		    name.setText(itemList.getName());
			balance = (EditText) newFormView.findViewById(R.id.balanceAccountEditText);
			balance.setText(String.format("%.02f", itemList.getBalance()));
			balance.setEnabled(false);
			bank = null;
			agency = null;
			number = null;
			overdraft = null;
		}		
		
		AlertDialog.Builder builderEditDialog = new AlertDialog.Builder(this);
		builderEditDialog.setTitle(R.string.editAccount);
		builderEditDialog.setView(newFormView);
		builderEditDialog.setPositiveButton(R.string.save, 
				   							new DialogInterface.OnClickListener() {
				      							@Override
				      							public void onClick(DialogInterface dialog, int button) {
				      								try {
				      									if(typeAccount==TypeAccount.ACCOUNT) {
				      										accountDao.updateName(itemList.getId(), name.getText().toString()); 
				      									}
				      									else {
				      										bankAccountDao.update(itemList.getId(), name.getText().toString(), 
				      												              Double.parseDouble(overdraft.getText().toString()), 
				      												              bank.getText().toString(),
				      												              agency.getText().toString(),
				      												              number.getText().toString());
				      									}
				      									Alert.showShortAlert(EditAccountActivity.this, "Conta editada com sucesso!");
				      								}
				      								catch(Exception e) {
				      									Alert.showShortAlert(EditAccountActivity.this, e.getMessage());
				      								}
				      								finish();
				      							}
			       							});
		
		AlertDialog editAccount = builderEditDialog.create();
		editAccount.show();
		
	}//end onListItemClick
	
	@Override
	protected void onStop() {
		
		connection.close();
		super.onStop();
		
	}//end onStop
	
}//end class

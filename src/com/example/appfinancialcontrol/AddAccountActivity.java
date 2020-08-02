package com.example.appfinancialcontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;
import db.DatabaseConnector;
import gui.util.Alert;
import model.dao.AccountDao;

public class AddAccountActivity extends Activity {
	
	private RadioGroup optionsRadioGroup;
	private TableLayout formTableLayout;
	private Button saveAccountButton;
	private EditText nameAccountEditText;
	private EditText balanceAccountEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account);
		
		formTableLayout = (TableLayout) findViewById(R.id.formAddAccountsTableLayout);
		saveAccountButton = (Button) findViewById(R.id.addAccountButton);
		optionsRadioGroup = (RadioGroup) findViewById(R.id.typeAccountRadioGroup);
		
		optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View newFormView = null;
				
				switch(checkedId) {
					case R.id.typeAccountRadioButton:
				
						newFormView = inflater.inflate(R.layout.form_new_account, null);
						break;
					 
					case R.id.typeBankAccountRadioButton:
			
						newFormView = inflater.inflate(R.layout.form_new_bankaccount, null);
						break;
	
				}
				formTableLayout.removeAllViews();
				formTableLayout.addView(newFormView);
				nameAccountEditText = (EditText) findViewById(R.id.nameAccountEditText);
				balanceAccountEditText = (EditText) findViewById(R.id.balanceAccountEditText);
			}
		}); //end setOnCheckedChangeListener()
		
		saveAccountButton.setOnClickListener(handlerButton); 
		
	}//end onCreate()
	
	OnClickListener handlerButton = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			AccountDao accountDao = new AccountDao(new DatabaseConnector(AddAccountActivity.this));
			
			try {
				accountDao.insert(nameAccountEditText.getText().toString(),
				              	  Double.parseDouble(balanceAccountEditText.getText().toString()));
				Alert.showShortAlert(AddAccountActivity.this, "Conta adicionada com sucesso!");
				finish();
			}
			catch(Exception e) {
				Alert.showShortAlert(AddAccountActivity.this, e.getMessage());
			}
			
		}
	};
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

}

package com.example.appfinancialcontrol;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;

public class AddAccount extends Activity {
	
	private RadioGroup optionsRadioGroup;
	private TableLayout formTableLayout;
	private Button saveAccountButton;
	
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
			}
		}); //end setOnCheckedChangeListener()
		
		saveAccountButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast teste = Toast.makeText(AddAccount.this, "Clicou Gravar", Toast.LENGTH_SHORT);
				teste.show();
			}
		});

	}//end onCreate()
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

}

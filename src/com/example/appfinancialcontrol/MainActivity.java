package com.example.appfinancialcontrol;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import model.Account;
import model.BankAccount;
import model.Operacoes;

public class MainActivity extends Activity {

	private static final String TAG = "FinancialControl";
	private List<Account> accounts;   //manage repositories received from the data file
	private double sumAccountsBalance;   //store the balance of all repositories

	//reference views
	private TextView valueBalanceTextView;
	private TableLayout accountsTableLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		System.out.println("teste");
		valueBalanceTextView = (TextView) findViewById(R.id.valueBalanceTextView);
		accountsTableLayout = (TableLayout) findViewById(R.id.accountsTableLayout);
		
		Account teste1 = new Account("Dinheiro", 5.00);
		BankAccount teste2 = new BankAccount("Itaú", 50.00, 100.00);
		
		accounts = new LinkedList<Account>();
		accounts.add(teste1);
		accounts.add(teste2);
		
		if(accounts.isEmpty()) {
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View noAccountView = inflater.inflate(R.layout.no_account_view, null);
			TextView message = (TextView) noAccountView.findViewById(R.id.no_account);
			message.setText(String.format("%s%n%s", "Você ainda não possui contas cadastradas.","Acesse o menu \"Contas\" e crie uma."));
			accountsTableLayout.addView(noAccountView);
		}
		else {
			makeAccountsGUI();
		
			try {
				sumAccountsBalance = Operacoes.sumAccountsBalance(accounts); 
			}
			catch(Exception e) {
				sumAccountsBalance = 0.0;
				Log.e(TAG,e.getMessage());
			}
		
			if(sumAccountsBalance > 0.0)   //if positive balance apply the color blue else apply the color red
				valueBalanceTextView.setTextColor(getResources().getColor(R.color.positiveBalance));
			else if(sumAccountsBalance < 0.0)
				valueBalanceTextView.setTextColor(getResources().getColor(R.color.negativeBalance));
		
			valueBalanceTextView.setText(String.format("%s %.02f", getResources().getString(R.string.cipher), sumAccountsBalance));   //show the total balance of all accounts
		}
		
	}//end onCreate() 

	public void makeAccountsGUI() {  //show the accounts 
		ListIterator<Account> iterator = accounts.listIterator();
		 
		while(iterator.hasNext()) {
			Account account = iterator.next();
			makeAccountGUIRow(account.getName(), account.getBalance());
		}
		
	}//end of makeAccountsGUI()
	
	public void makeAccountGUIRow(String name, double balance) { //creates the row of account using an inflater object
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
		View newAccountView = inflater.inflate(R.layout.new_account_view, null);
		TextView newNameAccount = (TextView) newAccountView.findViewById(R.id.accountNameTextView);
		TextView newBalanceAccount = (TextView) newAccountView.findViewById(R.id.accountBalanceTextView);
			
		newNameAccount.setText(name);
		newBalanceAccount.setText(String.format("%s %.02f", getResources().getString(R.string.cipher), balance));
			
		accountsTableLayout.addView(newAccountView);

	}//end makeAccountsGUI()

	
	//sets up a menu to manage the app's features
	private final int ACCOUNTS_MENU_ID = Menu.FIRST;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		menu.add(Menu.NONE, ACCOUNTS_MENU_ID, Menu.NONE, R.string.accounts_menu);
		
		return true;
	}
	
}//end MainActivity

package com.example.appfinancialcontrol;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TextView;
import model.BankAccount;
import model.FinancialRepository;
import model.Operacoes;

public class MainActivity extends Activity {
	
	private static final ColorStateList color = null;

	private List<FinancialRepository> accounts;

	private TextView valueBalanceTextView;
	private TableLayout accountsTableLayout;
	private Button accountsButton;
	
	private double sumAccountsBalance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		valueBalanceTextView = (TextView) findViewById(R.id.valueBalanceTextView);
		accountsTableLayout = (TableLayout) findViewById(R.id.accountsTableLayout);
		accountsButton = (Button) findViewById(R.id.accountsButton);
		
		FinancialRepository teste1 = new FinancialRepository("Dinheiro", 5.00);
		BankAccount teste2 = new BankAccount("Itaú", 50.00, 100.00);
		
		accounts = new LinkedList<FinancialRepository>();
		accounts.add(teste1);
		accounts.add(teste2);
		
		sumAccountsBalance = Operacoes.sumAccountsBalance(accounts);
		if(sumAccountsBalance > 0.0)
			valueBalanceTextView.setTextColor(getResources().getColor(R.color.positiveBalance));
		else if(sumAccountsBalance < 0.0)
			valueBalanceTextView.setTextColor(getResources().getColor(R.color.negativeBalance));
		
		valueBalanceTextView.setText(String.format("%s %.02f", getResources().getString(R.string.cipher), sumAccountsBalance));
		
		accountsButton.setOnClickListener(accountsButtonListener);
		
		if(!accounts.isEmpty())
			makeAccountsGUI();
		else
			System.out.println("Voce ainda não possui caixas cadastrados");
		
	}//end onCreate()
	
	public void makeAccountsGUI() {
		
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		ListIterator<FinancialRepository> iterator = accounts.listIterator();
	 
		while(iterator.hasNext()) {
			View newAccountView = inflater.inflate(R.layout.new_account_view, null);
			TextView newNameAccount = (TextView) newAccountView.findViewById(R.id.accountNameTextView);
			TextView newBalanceAccount = (TextView) newAccountView.findViewById(R.id.accountBalanceTextView);
			
			FinancialRepository account = iterator.next(); 
			
			newNameAccount.setText(account.getName());
			newBalanceAccount.setText(String.format("%s %.02f", getResources().getString(R.string.cipher), account.getBalance()));
			
			accountsTableLayout.addView(newAccountView);
		} //end while
		 
	}//end makeAccountsGUI()
	
	public void showAccountsMenu(View v) {
		PopupMenu popup = new PopupMenu(this, v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.main, popup.getMenu());
		popup.show();
	}
	
	private OnClickListener accountsButtonListener = new OnClickListener() {
												     @Override
												  	 public void onClick(View v) {
												  	   showAccountsMenu(v);
												  	 }
												  };
	
	
	
	
	
}//end MainActivity

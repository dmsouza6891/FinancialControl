package model;

import java.util.List;
import java.util.ListIterator;

import android.widget.Toast;

public class Operacoes {

	public static double sumAccountsBalance(List<Account> accounts) throws Exception {
		
		ListIterator<Account> iterator = accounts.listIterator();
		double sum = 0;

		while(iterator.hasNext()) {
			sum += iterator.next().getBalance();
		}	
		
		return sum;
	}
	
	public static String[] namesAccounts(List<Account> accounts){
		
		String[] result = new String[accounts.size()];
		ListIterator<Account> iterator = accounts.listIterator();
		int index=0;
		while(iterator.hasNext()) {
			result[index] = iterator.next().getName();
			index++;
		}	

		return result;
	}
	
	public static int idByName(List<Account> accounts, String name) {
		
		ListIterator<Account> iterator = accounts.listIterator();
		int result = -1;
		while(iterator.hasNext() && result < 0) {
			Account account = iterator.next();
			if(account.getName().equals(name)) 
				result = account.getId();
	
		}	
		return result;
	}
	
}

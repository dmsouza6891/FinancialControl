package model;

import java.util.List;
import java.util.ListIterator;

public class Operacoes {

	public static double sumAccountsBalance(List<FinancialRepository> accounts) {
		
		ListIterator<FinancialRepository> iterator = accounts.listIterator();
		double sum = 0;
		
		while(iterator.hasNext()) {
			sum += iterator.next().getBalance();
		}
		
		return sum;
	}
}

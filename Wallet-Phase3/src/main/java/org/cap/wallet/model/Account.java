package org.cap.wallet.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Account {
	
	public enum AccountType{
		CHECKINGS,SAVINGS;
}
	
	private double balance;
	private int accountId;
	private List<Transaction> transactions;
	private LocalDate openingDate;
	private String description;
	private AccountType accountType;
	
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public LocalDate getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	public Account() {
		
	}
	public Account(double balance, int accountId, List<Transaction> transactions, LocalDate openingDate,
			String description, AccountType accountType) {
		super();
		this.balance = balance;
		this.accountId = accountId;
		this.transactions = transactions;
		this.openingDate = openingDate;
		this.description = description;
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accountId=" + accountId + 
				 ", openingDate=" + openingDate + ", description=" + description + ", accountType=" + accountType
				+ "]";
	}

	
	
	

}
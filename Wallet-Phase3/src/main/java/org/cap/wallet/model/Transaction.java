package org.cap.wallet.model;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {

	public enum TransactionType{
		CREDIT,DEBIT;
}
	private LocalDate transactionDate;
	private double transactionamount;
	private String transactionDescription;
	private Account fromAccount;
	private Account toAccount;
	private TransactionType transactionType;
	
	public Transaction(LocalDate transactionDate, double transactionamount, String transactionDescription,
			Account fromAccount, Account toAccount, TransactionType transactionType) {
		super();
		this.transactionDate = transactionDate;
		this.transactionamount = transactionamount;
		this.transactionDescription = transactionDescription;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.transactionType = transactionType;
		
	}
	

	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate localDate) {
		this.transactionDate = localDate;
	}
	public double getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(double transactionamount) {
		this.transactionamount = transactionamount;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}


	@Override
	public String toString() {
		return "Transaction [transactionDate=" + transactionDate + ", transactionamount=" + transactionamount
				+ ", transactionDescription=" + transactionDescription + ", fromAccount=" + fromAccount + ", toAccount="
				+ toAccount + ", transactionType=" + transactionType + "]";
	}
	}
	
	

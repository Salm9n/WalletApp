package org.cap.wallet.dao;

import java.sql.Date;
import java.util.List;

import org.cap.wallet.model.Account;
import org.cap.wallet.model.Transaction;
import org.cap.wallet.model.User;

public interface IAccountDao {
	
	public Account addAccount(Account account, User user);
	public Account getAccount(int accountId);
	public Account getDepositAmt(Account account);
	public Account getWithdrawAmt (Account account);
	public Account addTransaction (Account account, Transaction transaction);
	public List<Transaction> getTransactions (Account account);
	public List<Transaction> getTransactionsFromDate(Date startDate, Date endDate, Account account);
	public List<Account> printAccounts(int userId);
}

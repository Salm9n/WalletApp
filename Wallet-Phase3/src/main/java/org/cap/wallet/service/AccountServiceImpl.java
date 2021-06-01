package org.cap.wallet.service;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import org.cap.wallet.dao.AccountDBDaoImpl;
import org.cap.wallet.dao.IAccountDao;
import org.cap.wallet.model.Account;
import org.cap.wallet.model.Transaction;
import org.cap.wallet.model.User;

public class AccountServiceImpl implements IAccountService{
	
	IAccountDao accountDao = new AccountDBDaoImpl();


	@Override
	public Account addAccount(Account account, User user) {
		Random r = new Random();
		int low = 100000;
		int high = 999999;
		int userId = r.nextInt(high-low) + low;
		account.setAccountId(userId);	
		
		return accountDao.addAccount(account, user);
	
	}


	@Override
	public Account getAccount(int accountId) {
		
		return accountDao.getAccount(accountId);
	}


	@Override
	public Account getDepositAmt(Account account) {
		
		return accountDao.getDepositAmt(account);
		
	}


	@Override
	public Account getWithdrawAmt(Account account) {
		
		return accountDao.getWithdrawAmt(account);
	}


	@Override
	public Account addTransaction(Account account, Transaction transaction) {
		// TODO Auto-generated method stub
		return accountDao.addTransaction(account, transaction);
	}




	@Override
	public List<Account> printAccounts(int userId) {
		// TODO Auto-generated method stub
		return accountDao.printAccounts(userId);
	}


	@Override
	public List<Transaction> getTransactions(Account account) {
		// TODO Auto-generated method stub
		return accountDao.getTransactions(account);
	}


	@Override
	public List<Transaction> getTransactionsFromDate(Date startDate, Date endDate, Account account) {
		// TODO Auto-generated method stub
		return accountDao.getTransactionsFromDate(startDate, endDate, account);
	}



}

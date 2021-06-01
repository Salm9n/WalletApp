package org.cap.wallet.service;

import java.util.List;

import org.cap.wallet.model.Account;
import org.cap.wallet.model.Transaction;
import org.cap.wallet.model.User;

public interface IUserService {
	
	public List<User> addUser(User user);
	public User login(String email, String password);
	public Transaction deposit (Account fromAccount, Account toAccount, String transaction, double depositAmt); 
	public Transaction withdraw (Account fromAccount, Account toAccount, String string, double d);
	public Transaction transfer(Account fromAccount, Account toAccount, String string, double d);
	public List<Account> showAllUserAccounts(User user);
	public Account addAccount(Account account, User user);

}

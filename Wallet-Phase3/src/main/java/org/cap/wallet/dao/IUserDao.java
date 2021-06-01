package org.cap.wallet.dao;

import java.util.List;

import org.cap.wallet.model.Account;
import org.cap.wallet.model.Transaction;
import org.cap.wallet.model.User;

public interface IUserDao {
	
	public List<User> addUser(User user);
	public User login(String email, String password);
	public Transaction deposit (Account fromAccount, Account toAccount, String description, double depositAmt); 
	public Transaction withdraw (Account fromAccount, Account toAccount, String string, double d);
	public Transaction transfer(Account fromAccount, Account toAccount, String string, double d);
//	public List<User> showAllUserAccounts(List<User> users);
	public List<Account> showAllUserAccounts(User user);
	public Account addAccount(Account account, User user);

}

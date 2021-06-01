package org.cap.wallet.service;

import java.util.List;
import java.util.Random;

import org.cap.wallet.dao.IUserDao;
import org.cap.wallet.dao.UserDBDaoImpl;
import org.cap.wallet.dao.UserDaoImpl;
import org.cap.wallet.model.Account;
import org.cap.wallet.model.Transaction;
import org.cap.wallet.model.User;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDao = new UserDBDaoImpl();

	@Override
	public List<User> addUser(User user) {
		Random r = new Random();
		int low = 1000;
		int high = 9999;
		int userId = r.nextInt(high-low) + low;
		user.setUserId(userId);	
		
		return userDao.addUser(user);
	}

	@Override
	public User login(String email, String password) {
			
		
		return userDao.login(email,password);
	}
//
//	@Override
//	public Transaction deposit(Account fromAccount, Account toAccount) {
//
//		return userDao.deposit(fromAccount, toAccount);
//		//return null;
//	}

//	@Override
//	public Transaction withdraw(Account fromAccount, Account toAccount) {
//		
//		return userDao.withdraw(fromAccount, toAccount);
//	}


//	@Override
//	public Transaction transfer(Account fromAccount, Account toAccount) {
//		// TODO Auto-generated method stub
//		return userDao.transfer(fromAccount, toAccount);
//	}

	@Override
	public List<Account> showAllUserAccounts(User user) {
	
		return userDao.showAllUserAccounts(user);
	}

	@Override
	public Account addAccount(Account account, User user) {
		// TODO Auto-generated method stub
		return userDao.addAccount(account, user);
	}

	@Override
	public Transaction deposit(Account fromAccount, Account toAccount, String transaction, double depositAmt) {
		// TODO Auto-generated method stub
		return userDao.deposit(fromAccount, toAccount, transaction, depositAmt);
	}

	@Override
	public Transaction withdraw(Account fromAccount, Account toAccount, String string, double d) {
		// TODO Auto-generated method stub
		return userDao.withdraw(fromAccount, toAccount, string, d);
	}

	@Override
	public Transaction transfer(Account fromAccount, Account toAccount, String string, double d) {
		// TODO Auto-generated method stub
		return userDao.transfer(fromAccount, toAccount,string,d);
	}

}

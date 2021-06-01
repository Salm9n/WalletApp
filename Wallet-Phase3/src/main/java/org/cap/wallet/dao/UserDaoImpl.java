package org.cap.wallet.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.cap.wallet.model.Account;
import org.cap.wallet.model.Transaction;
import org.cap.wallet.model.Transaction.TransactionType;
import org.cap.wallet.model.User;
import org.cap.wallet.exception.*;

public class UserDaoImpl implements IUserDao{
	
	private List<User> userDB = new ArrayList<User>();
	public List<User> getUserDB() {
		return userDB;
	}

	public void setUserDB(List<User> userDB) {
		this.userDB = userDB;
	}

	Scanner sc = new Scanner(System.in);

	@Override
	public List<User> addUser(User user) {
		
		userDB.add(user);
		
		for (User user1: userDB) {
			System.out.println(user1.toString());
		}
		return userDB;
	}

	@Override
	public User login(String email, String password) {
		for (User user: userDB) {
			if (user.getEmailId().equals(email) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

//	@Override
//	public Transaction deposit(Account fromAccount, Account toAccount) {
//		
//		Transaction transaction = new Transaction();
//		TransactionType transactionType = TransactionType.CREDIT;
//		System.out.println("Enter a description for this transaction.");
//		String description = sc.nextLine();
//		System.out.println("How much do you want to deposit?");
//		double depositAmt = sc.nextDouble();
//		double currentBalance = fromAccount.getBalance();
//		double newBalance = depositAmt + currentBalance;
//		fromAccount.setBalance(newBalance);
//		System.out.println("Deposit successful");
//		System.out.println("New balance: " + fromAccount.getBalance());
//		transaction.setFromAccount(fromAccount);
//		transaction.setToAccount(toAccount);
//		transaction.setTransactionamount(depositAmt);
//		transaction.setTransactionDate(LocalDate.now());
//		transaction.setTransactionDescription(description);
//		transaction.setTransactionType(transactionType);
//		
//		
//		
//		return transaction;
//	}

//	@Override
//	public Transaction withdraw(Account fromAccount, Account toAccount) {
//		Transaction transaction = new Transaction();
//		TransactionType transactionType = TransactionType.DEBIT;
//		System.out.println("Enter a description for this transaction.");
//		String description = sc.nextLine();
//		double withdrawAmt = 0;
//		double newBalance = 0;
//		do {
//		System.out.println("How much do you want to withdraw");
//	    withdrawAmt = sc.nextDouble();
//		double currentBalance = fromAccount.getBalance();
//	    newBalance = currentBalance - withdrawAmt;
//	    if (newBalance < -1) {
//	    	System.out.println("Cant withdraw more then balance amount!");
//	    }
//		} while (newBalance < -1);
//		fromAccount.setBalance(newBalance);
//		System.out.println("Withdrawl successful");
//		System.out.println("New balance: " + fromAccount.getBalance());
//		transaction.setFromAccount(fromAccount);
//		transaction.setToAccount(toAccount);
//		transaction.setTransactionamount(withdrawAmt);
//		transaction.setTransactionDate(LocalDate.now());
//		transaction.setTransactionDescription(description);
//		transaction.setTransactionType(transactionType);
//		
//		
//		
//		
//		
//		return transaction;
//	}

//	@Override
//	public User showAllUserAccounts(User sampel) {
//		User user1 = new User();
//		for (User user: userDB) {
//			if (user.getAccounts()!= null) {
//				for (Account account: user.getAccounts()) {
//					System.out.println(account.toString());
//				}
//			}
//			else {
//				System.out.println("No accounts here!");
//			}
//		}
//		
//		return user1;
//	}

//	@Override
//	public Transaction transfer(Account fromAccount, Account toAccount) {
//		
//		Transaction transaction = new Transaction();
//		TransactionType transactionType = TransactionType.CREDIT;
//		double transferAmt = 0;
//		double firstAccBal = fromAccount.getBalance();
//		double secondAccBal = toAccount.getBalance();
//		boolean valid = false;
//		System.out.println("Enter a description for this transaction.");
//		String description = sc.nextLine();
//		
//		System.out.println("How much do you want to transfer");
//		while(!valid) {
//			try {
//	    transferAmt = sc.nextDouble();
//	    if (transferAmt < 500) {
//	    	throw new InvalidTransferException("Must be greater than 500!"); }
//	    else {
//	    	valid = true;
//	    } }
//		catch (InvalidTransferException ex) {
//			valid = false;
//			System.out.println("Must be greater than 500");
//		}
//	    }
//		System.out.println("Is this a debit(1) or a credit(2) transaction?");
//		int choice = sc.nextInt();
//		sc.nextLine();
//		switch (choice) {
//		case 1 :
//			transactionType = TransactionType.CREDIT;
//		
//		case 2:
//			transactionType = TransactionType.DEBIT;
//			
//		default:
//			System.out.println("Enter 1 or 2");
//		}
//		firstAccBal = firstAccBal - transferAmt;
//		secondAccBal += transferAmt;
//		
//		fromAccount.setBalance(firstAccBal);
//		toAccount.setBalance(secondAccBal);
//		System.out.println("Transfer successful!");
//		System.out.println("First Account New Balance: " + fromAccount.getBalance());
//		System.out.println("Second Account New Balance: " + toAccount.getBalance());
//		
//		transaction.setFromAccount(fromAccount);
//		transaction.setToAccount(toAccount);
//		transaction.setTransactionamount(transferAmt);
//		transaction.setTransactionDate(LocalDate.now());
//		transaction.setTransactionDescription(description);
//		transaction.setTransactionType(transactionType);
//		
//		return transaction;
//	}

	@Override
	public Account addAccount(Account account, User user) {
return null;
		
	}

	@Override
	public Transaction deposit(Account fromAccount, Account toAccount, String description, double depositAmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction withdraw(Account fromAccount, Account toAccount, String string, double d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction transfer(Account fromAccount, Account toAccount, String string, double d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> showAllUserAccounts(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}

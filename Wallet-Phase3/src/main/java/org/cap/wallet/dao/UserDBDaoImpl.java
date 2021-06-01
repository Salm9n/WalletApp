package org.cap.wallet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.cap.wallet.exception.InvalidTransferException;
import org.cap.wallet.model.Account;
import org.cap.wallet.model.Account.AccountType;
import org.cap.wallet.model.Transaction;
import org.cap.wallet.model.User;
import org.cap.wallet.model.Transaction.TransactionType;


public class UserDBDaoImpl implements IUserDao {
	
	Scanner sc = new Scanner(System.in);
	
	private Connection getDBConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");		//1. Load Driver Class
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capg_wallet","root", "admin");	//2. Get connection
	
		return connection;
}

	@Override
	public List<User> addUser(User user) {
		
		try(Connection conn = getDBConnection()) {
		String sql = "insert into user(firstname, lastname,password,emailid,dateOfBirth,ssn) "
				+ "values(?,?,?,?,?,?)";
			
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, user.getFirstName());
		pst.setString(2, user.getLastName());
		pst.setString(3, user.getPassword());
		pst.setString(4, user.getEmailId());
		pst.setDate(5, Date.valueOf(user.getDOB()));
		pst.setString(6, user.getSSN());
		
		int count = pst.executeUpdate();
		if (count > 0) {
			String getUserId = "select max(userid) from user";
			PreparedStatement pst1 = conn.prepareStatement(getUserId);
			ResultSet rs = pst1.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getInt(1));
			}
		}
		
		
		String sql2 = "insert into address(houseNumber,streetName,city,state,zipcode,country,userid)"
				 	+ "values(?,?,?,?,?,?,?)";
		
		PreparedStatement pst3 = conn.prepareStatement(sql2);
		pst3.setString(1, user.getAddress().getHouseNumber());
		pst3.setString(2, user.getAddress().getStreetName());
		pst3.setString(3, user.getAddress().getCity());
		pst3.setString(4, user.getAddress().getState());
		pst3.setInt(5, user.getAddress().getZipcode());
		pst3.setString(6, user.getAddress().getCountry());
		pst3.setInt(7, user.getUserId());
		
		int count2 = pst3.executeUpdate();
		
		if (count2 > 0) {
			System.out.println("Success");
		}
		else {
			System.out.println("Error");
		}
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User login(String email, String password) {
		User user = null;
		try(
			Connection conn = getDBConnection()) {
			
		
		String sql = "select * from user where emailId=? and password=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, email);
		pst.setString(2, password);
		
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {
			user = new User();
			user.setUserId(rs.getInt("userid"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setPassword(rs.getString("password"));
			user.setDOB(rs.getDate("dateOfBirth").toLocalDate());
			user.setSSN(rs.getString("ssn"));
			user.setEmailId(rs.getString("emailId"));
		}
		
		//fetch address
		
		
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(user.getUserId());
		return user;
	}

	@Override
	public Transaction deposit(Account fromAccount, Account toAccount, String description, double depositAmt) {
		double balance = 0;
		Transaction transaction = null;
		try(
				Connection conn = getDBConnection()) {
			
			String sql = "select balance from account where accountid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, fromAccount.getAccountId());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				balance = rs.getDouble(1);
			}
			transaction = new Transaction();
			TransactionType transactionType = TransactionType.CREDIT;
			System.out.println("Enter a description for this transaction.");
			//String description = sc.nextLine();
			System.out.println("How much do you want to deposit?");
			//double depositAmt = sc.nextDouble();
			double newAmt = balance + depositAmt;
			
			String sql3 = "update account set balance =? where accountid=?";
			PreparedStatement pst3 = conn.prepareStatement(sql3);
			pst3.setDouble(1, newAmt);
			pst3.setInt(2, fromAccount.getAccountId());
			pst3.executeUpdate();
			
			fromAccount.setBalance(newAmt);
			System.out.println("Deposit successful");
			System.out.println("New balance: " + fromAccount.getBalance());
			transaction.setFromAccount(fromAccount);
			transaction.setToAccount(toAccount);
			transaction.setTransactionamount(depositAmt);
			transaction.setTransactionDate(LocalDate.now());
			transaction.setTransactionDescription(description);
			transaction.setTransactionType(transactionType);
			
			String sql2 = "insert into transaction(transactionDate,transactionAmount,transactionDescription,transactionType,fromid,toid)" + 
							"values(?,?,?,?,?,?)";
			
			PreparedStatement pst2 = conn.prepareStatement(sql2);
		
			pst2.setDate(1, Date.valueOf(transaction.getTransactionDate()));
			pst2.setDouble(2, transaction.getTransactionamount());
			pst2.setString(3, transaction.getTransactionDescription());
			pst2.setString(4, "CREDIT");
			pst2.setInt(5, fromAccount.getAccountId());
			pst2.setInt(6, fromAccount.getAccountId());
			
			int count = pst2.executeUpdate();
			
			if (count > 0) {
				System.out.println("Success");
			}
			else {
				System.out.println("Error");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return transaction;
	}

	@Override
	public Transaction withdraw(Account fromAccount, Account toAccount, String description, double withdrawAmt) {
		double balance = 0;
		Transaction transaction = null;
		try(
				Connection conn = getDBConnection()) {
			
			String sql = "select balance from account where accountid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, fromAccount.getAccountId());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				balance = rs.getDouble(1);
			}
		    transaction = new Transaction();
			TransactionType transactionType = TransactionType.DEBIT;
			System.out.println("Enter a description for this transaction.");
			//String description = sc.nextLine();
			//double withdrawAmt = 0;
			double newBalance = 0;
			do {
			System.out.println("How much do you want to withdraw");
		  //  withdrawAmt = sc.nextDouble();
			double currentBalance = fromAccount.getBalance();
		    newBalance = currentBalance - withdrawAmt;
		    if (newBalance < -1) {
		    	System.out.println("Cant withdraw more then balance amount!");
		    }
			} while (newBalance < -1);
			
			String sql3 = "update account set balance =? where accountid=?";
			PreparedStatement pst3 = conn.prepareStatement(sql3);
			pst3.setDouble(1, newBalance);
			pst3.setInt(2, fromAccount.getAccountId());
			pst3.executeUpdate();
			
			fromAccount.setBalance(newBalance);
			System.out.println("Withdrawl successful");
			System.out.println("New balance: " + fromAccount.getBalance());
			transaction.setFromAccount(fromAccount);
			transaction.setToAccount(toAccount);
			transaction.setTransactionamount(withdrawAmt);
			transaction.setTransactionDate(LocalDate.now());
			transaction.setTransactionDescription(description);
			transaction.setTransactionType(transactionType);
			
			String sql2 = "insert into transaction(transactionDate,transactionAmount,transactionDescription,transactionType,fromid,toid)" + 
					"values(?,?,?,?,?,?)";
	
			PreparedStatement pst2 = conn.prepareStatement(sql2);
		
			pst2.setDate(1, Date.valueOf(transaction.getTransactionDate()));
			pst2.setDouble(2, transaction.getTransactionamount());
			pst2.setString(3, transaction.getTransactionDescription());
			pst2.setString(4, "DEBIT");
			pst2.setInt(5, fromAccount.getAccountId());
			pst2.setInt(6, fromAccount.getAccountId());
			
			int count = pst2.executeUpdate();
			
			if (count > 0) {
				System.out.println("Success");
			}
			else {
				System.out.println("Error");
			
			}
			
			
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transaction;
	}

	@Override
	public Transaction transfer(Account fromAccount, Account toAccount, String description, double transferAmt) {
		Transaction transaction = new Transaction();
		TransactionType transactionType = TransactionType.DEBIT;
	//	double transferAmt = 0;
		double firstAccBal = 0;
		double secondAccBal = 0;
		boolean valid = false;
		try(
				Connection conn = getDBConnection()) {
			
			String sql = "select balance from account where accountid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, fromAccount.getAccountId());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				firstAccBal = rs.getDouble(1);
			}
			
			String sql1 = "select balance from account where accountid=?";
			PreparedStatement pst1 = conn.prepareStatement(sql);
			pst1.setInt(1, toAccount.getAccountId());
			
			ResultSet rs1 = pst1.executeQuery();
			
			while (rs1.next()) {
				secondAccBal = rs1.getDouble(1);
			}
			System.out.println("second bal: " + secondAccBal);
			System.out.println("Enter a description for this transaction.");
		//	String description = sc.nextLine();
			
//			System.out.println("How much do you want to transfer");
//			while(!valid) {
//				try {
//		    transferAmt = sc.nextDouble();
//		    if (transferAmt > firstAccBal) {
//		    	throw new InvalidTransferException("Insufficient balance to transfer!"); }
//		    else {
//		    	valid = true;
//		    } }
//			catch (InvalidTransferException ex) {
//				valid = false;
//				System.out.println("Insufficient balance to transfer!");
//			}
//		    }
			
			firstAccBal = firstAccBal - transferAmt;
			secondAccBal += transferAmt;
			
			String sql2 = "update account set balance=? where accountid=?";
			PreparedStatement pst3 = conn.prepareStatement(sql2);
			pst3.setDouble(1, firstAccBal);
			pst3.setInt(2, fromAccount.getAccountId());
			pst3.executeUpdate();
			
			String sql3 = "update account set balance=? where accountid=?";
			PreparedStatement pst4 = conn.prepareStatement(sql3);
			pst4.setDouble(1, secondAccBal);
			pst4.setInt(2, toAccount.getAccountId());
			pst4.executeUpdate();
			
			transaction.setFromAccount(fromAccount);
			transaction.setToAccount(toAccount);
			transaction.setTransactionamount(transferAmt);
			transaction.setTransactionDate(LocalDate.now());
			transaction.setTransactionDescription(description);
			transaction.setTransactionType(transactionType);
			
			String sql4 = "insert into transaction(transactionDate,transactionAmount,transactionDescription,transactionType,fromid,toid)" + 
					"values(?,?,?,?,?,?)";
	
			PreparedStatement pst2 = conn.prepareStatement(sql4);
		
			pst2.setDate(1, Date.valueOf(transaction.getTransactionDate()));
			pst2.setDouble(2, transaction.getTransactionamount());
			pst2.setString(3, transaction.getTransactionDescription());
			pst2.setString(4, "DEBIT");
			pst2.setInt(5, fromAccount.getAccountId());
			pst2.setInt(6, fromAccount.getAccountId());
			
			int count = pst2.executeUpdate();
			
			if (count > 0) {
				System.out.println("Success");
			}
			else {
				System.out.println("Error");
			
			}
			
			String sql5 = "insert into transaction(transactionDate,transactionAmount,transactionDescription,transactionType,fromid,toid)" + 
					"values(?,?,?,?,?,?)";
	
			PreparedStatement pst5 = conn.prepareStatement(sql5);
		
			pst5.setDate(1, Date.valueOf(transaction.getTransactionDate()));
			pst5.setDouble(2, transaction.getTransactionamount());
			pst5.setString(3, transaction.getTransactionDescription());
			pst5.setString(4, "CREDIT");
			pst5.setInt(5, fromAccount.getAccountId());
			pst5.setInt(6, fromAccount.getAccountId());
			
			int count2 = pst5.executeUpdate();
			
			if (count2 > 0) {
				System.out.println("Success");
			}
			else {
				System.out.println("Error");
			
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transaction;
	}

	@Override
	public List<Account> showAllUserAccounts(User user) {
		List<Account> accounts = new ArrayList<Account>();
		try(
				Connection conn = getDBConnection()) {
			
			String sql = "select * from account where userid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user.getUserId());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setOpeningDate(rs.getDate("openingDate").toLocalDate());
				account.setDescription(rs.getString("description"));
				account.setBalance(rs.getDouble("balance"));
				if (rs.getString("accountType").equals("CHECKINGS"))
					account.setAccountType(AccountType.CHECKINGS);
				account.setAccountType(AccountType.SAVINGS);
				
				accounts.add(account);
			}
			
			for (Account account: accounts) {
				System.out.println(account.toString());
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public Account addAccount(Account account, User user) {
		
		try(Connection conn = getDBConnection()) {
			String sql = "insert into account(openingDate, description, balance, accountType, userid)" 
						+ "values (?,?,?,?,?)";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDate(1, Date.valueOf(account.getOpeningDate()));
			pst.setString(2, account.getDescription());
			pst.setDouble(3,account.getBalance());
			pst.setString(4, account.getAccountType().toString());
			pst.setInt(5, user.getUserId());
			
			int count = pst.executeUpdate();
			
			if (count > 0) {
				System.out.println("Success");
			}
			else {
				System.out.println("Error");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

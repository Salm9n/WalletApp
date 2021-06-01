package org.cap.wallet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cap.wallet.model.Account;
import org.cap.wallet.model.Transaction;
import org.cap.wallet.model.User;
import org.cap.wallet.model.Account.AccountType;
import org.cap.wallet.model.Transaction.TransactionType;

public class AccountDBDaoImpl implements IAccountDao{
	
	private Connection getDBConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");		//1. Load Driver Class
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/capg_wallet","root", "admin");	//2. Get connection
	
		return connection;
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

	@Override
	public Account getAccount(int accountId) {
		Account account = null;
		try(Connection conn = getDBConnection()) {
		
			String sql = "select * from account where accountid=?";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, accountId);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				
				account = new Account();
				account.setAccountId(rs.getInt("accountid"));
				account.setOpeningDate(rs.getDate("openingDate").toLocalDate());
				account.setDescription(rs.getString("description"));
				account.setBalance(rs.getDouble("balance"));
				if (rs.getString("accountType").equals("CHECKINGS"))
					account.setAccountType(AccountType.CHECKINGS);
				account.setAccountType(AccountType.SAVINGS);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account getDepositAmt(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getWithdrawAmt(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account addTransaction(Account account, Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactions(Account account) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try(Connection conn = getDBConnection()) {
			
			String sql = "select * from transaction where fromid=? OR toid=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, account.getAccountId());
			pst.setInt(2, account.getAccountId());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionDate(rs.getDate("transactionDate").toLocalDate());
				transaction.setTransactionamount(rs.getDouble("transactionAmount"));
				transaction.setTransactionDescription(rs.getString("transactionDescription"));
				
				if (rs.getString("transactionType").contentEquals("CREDIT")) {
					transaction.setTransactionType(TransactionType.CREDIT);
				}
				else {
					transaction.setTransactionType(TransactionType.DEBIT);
				}
				transaction.setFromAccount(getAccount(rs.getInt("fromid")));
				transaction.setToAccount(getAccount(rs.getInt("toid")));
				
				transactions.add(transaction);
			}
			
			for (Transaction trans: transactions ) {
				System.out.println(trans.toString());
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public List<Account> printAccounts(int userId) {
		List<Account> accounts = new ArrayList<Account>();
		try(
				Connection conn = getDBConnection()) {
			
			String sql = "select * from account where userid != ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
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
	public List<Transaction> getTransactionsFromDate(Date startDate, Date endDate, Account account) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try(Connection conn = getDBConnection()) {
			
			String sql = "select * from transaction where transactionDate >= ? AND transactionDate <= ? AND (fromid = ? OR toid = ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, startDate.toString());
			pst.setString(2, endDate.toString());
			pst.setInt(3, account.getAccountId());
			pst.setInt(4, account.getAccountId());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionDate(rs.getDate("transactionDate").toLocalDate());
				transaction.setTransactionamount(rs.getDouble("transactionAmount"));
				transaction.setTransactionDescription(rs.getString("transactionDescription"));
				
				if (rs.getString("transactionType").contentEquals("CREDIT")) {
					transaction.setTransactionType(TransactionType.CREDIT);
				}
				else {
					transaction.setTransactionType(TransactionType.DEBIT);
				}
				transaction.setFromAccount(getAccount(rs.getInt("fromid")));
				transaction.setToAccount(getAccount(rs.getInt("toid")));
				
				transactions.add(transaction);
			}
			
			for (Transaction trans: transactions ) {
				System.out.println(trans.toString());
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

}

package org.cap.wallet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cap.wallet.model.Account;
import org.cap.wallet.model.User;
import org.cap.wallet.service.IUserService;
import org.cap.wallet.service.UserServiceImpl;

/**
 * Servlet implementation class ShowTransactionsServlet
 */
@WebServlet("/ShowBalanceServlet")
public class ShowBalanceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		IUserService userService = new UserServiceImpl();
		List<Account> accounts = userService.showAllUserAccounts(user);
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"ISO-8859-1\">\r\n" + 
				"<title>Main Page</title>\r\n" + 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"myStyles.css\">\r\n" + 
				" <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<nav class=\"navbar navbar-default\">\r\n" + 
				"  <div class=\"container-fluid\">\r\n" + 
				"    <!-- Brand and toggle get grouped for better mobile display -->\r\n" + 
				"    <div class=\"navbar-header\">\r\n" + 
				"      <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\r\n" + 
				"        <span class=\"sr-only\">Toggle navigation</span>\r\n" + 
				"        <span class=\"icon-bar\"></span>\r\n" + 
				"        <span class=\"icon-bar\"></span>\r\n" + 
				"        <span class=\"icon-bar\"></span>\r\n" + 
				"      </button>\r\n" + 
				"      <a class=\"navbar-brand\" href=\"#\">Capgemini Banking Portal</a>\r\n" + 
				"    </div>\r\n" + 
				"\r\n" + 
				"   <!-- Collect the nav links, forms, and other content for toggling -->\r\n" + 
				"    <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\r\n" + 
				"      <ul class=\"nav navbar-nav\">\r\n" + 
				"        <li class=\"active\"><a href=createAccount.html>Create Account <span class=\"sr-only\">(current)</span></a></li>\r\n" + 
				"        <li class=\"dropdown\">\r\n" + 
				"          <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Transactions <span class=\"caret\"></span></a>\r\n" + 
				"          <ul class=\"dropdown-menu\">\r\n" + 
				"            <li><a href=ShowDepositServlet>Deposit</a></li>\r\n" + 
				"            <li><a href=ShowWithdrawServlet>Withdraw</a></li>\r\n" + 
				"            <li><a href=ShowFundTransferServlet>Fund Transfer</a></li>\r\n" + 
				"            <li><a href=ShowBalanceServlet>Check Balance</a></li>\r\n" + 
				"            <li><a href=ShowTransactionServlet>Transaction History</a></li>\r\n" + 
				"          </ul>\r\n" + 
				"        </li>\r\n" + 
				"      </ul>\r\n" + 
				"      \r\n" + 
				"      <ul class=\"nav navbar-nav navbar-right\">\r\n" + 
				"        <li><a href=LogoutServlet>Logout</a></li>\r\n" + 
				"      </ul>\r\n" + 
				"    </div><!-- /.navbar-collapse -->\r\n" + 
				"  </div><!-- /.container-fluid -->\r\n" + 
				"</nav>\r\n" + 
				" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\r\n" + 
				"\r\n" + 
				"    <!-- JavaScript Bootstrap CDN -->\r\n" + 
				"    <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>\r\n" + 
				"<div>\r\n" + 
				"<h1 align=\"center\"></h1>\r\n" + 
				"</div>\r\n" + 
				"<div class = \"container\">\r\n" + 
				"<img src = \".\\css\\images\\capgemini logo.jpg\" class = \"inbl\"/>\r\n" + 
				"<div style=\"float:left;\">\r\n" + 
				"<div class=\"container\">\r\n" + 
				"  <h2>Balance</h2>\r\n" + 
				"  <form method = \"post\" action = \"BalanceServlet\">\r\n" + 
				"     <div class=\"form-group\">\r\n" + 
				"      <label for=\"account\">Enter Account Id:</label>\r\n");
		out.println("<select name =\"accId\">");
			for(Account account:accounts) {
				out.println("<option value = " + account.getAccountId() + ">" + account.getAccountId()+ "</option>");
			}
			out.println("</select>");
		 
				out.println(
				"    </div>\r\n" + 
				"    <button type=\"submit\" class=\"btn btn-default\">Submit</button>\r\n" + 
				"  </form>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</body>\r\n" + 
				"</html>");
				
	}
	

}

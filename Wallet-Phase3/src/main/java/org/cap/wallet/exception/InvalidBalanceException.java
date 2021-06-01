package org.cap.wallet.exception;

public class InvalidBalanceException extends RuntimeException{
	
	public InvalidBalanceException(String error) {
		super(error);
	}
	
	/*public InvalidBalanceException(String str) {
		super("The salary must be between 2000 and 10000");
	} */

}

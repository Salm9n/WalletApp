package org.cap.wallet.exception;

public class InvalidTransferException extends RuntimeException{
	
	public InvalidTransferException(String error) {
		super(error);
	}

}

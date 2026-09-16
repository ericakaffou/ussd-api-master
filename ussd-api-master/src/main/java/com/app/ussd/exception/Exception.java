package com.app.ussd.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class Exception extends RuntimeException{
	
	@Getter
	private ErrorCodes errorCode;
	
	@Getter
	private String detail;
	
	public Exception(String message) {		
		super(message);		
	}	
	
	public Exception(String message , ErrorCodes errorCode) {		
		super(message);
		this.errorCode = errorCode ;
		
	}
	
	public Exception(String message , ErrorCodes errorCode, String detail) {		
		super(message);
		this.errorCode = errorCode ;
		this.detail = detail ;
		
		
	}
	
}

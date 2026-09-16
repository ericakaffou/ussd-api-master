package com.app.ussd.exception;

import java.util.List;

import lombok.Getter;

@SuppressWarnings("serial")
public class InvalidEntityException  extends RuntimeException{
	
	@Getter
	private ErrorCodes errorCode;
	
	@Getter
	private ErrorCodes errorCode1;
	
	@Getter
	private List<String> errors;
	
	@Getter
	private String detail;
	
	public InvalidEntityException(String message) {		
		super(message);
		
	}
	
	public InvalidEntityException(String message , Throwable cause) {		
		super(message , cause );
		
	}
	
	public InvalidEntityException(String message , Throwable cause, ErrorCodes errorCode) {		
		super(message);
		this.errorCode = errorCode ;
		
	}
	public InvalidEntityException(String message , ErrorCodes errorCode) {		
		super(message);
		this.errorCode = errorCode ;
		
	}
	
	public InvalidEntityException(String message , ErrorCodes errorCode, String detail) {
		super(message);
		this.errorCode = errorCode ;
		this.detail = detail;
		
	}
	
	public InvalidEntityException(String message ,  ErrorCodes errorCode , List<String> errors) {		
		super(message);
		this.errorCode = errorCode ;
		this.errors = errors;
		
	}
	
	public InvalidEntityException(String message ,  ErrorCodes errorCode ,ErrorCodes errorCode1 , List<String> errors) {		
		super(message);
		this.errorCode = errorCode ;
		this.errorCode1 = errorCode1 ;
		this.errors = errors;
		
	}
	

}

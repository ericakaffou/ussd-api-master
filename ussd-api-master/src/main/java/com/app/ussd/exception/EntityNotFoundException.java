package com.app.ussd.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException{
	
	@Getter
	private ErrorCodes errorCode;
	
	@Getter
	private String detail;
	
	public EntityNotFoundException(String message) {		
		super(message);
		
	}
	
	public EntityNotFoundException(String message , Throwable cause) {		
		super(message , cause );
		
	}
	
	public EntityNotFoundException(String message , Throwable cause, ErrorCodes errorCode) {		
		super(message);
		this.errorCode = errorCode ;
		
	}

	public EntityNotFoundException(String message , ErrorCodes errorCode) {		
		super(message);
		this.errorCode = errorCode ;
		
	}
	
	public EntityNotFoundException(String message , ErrorCodes errorCode, String detail) {
		super(message);
		this.errorCode = errorCode ;
		this.detail = detail;
		
	}
	
}

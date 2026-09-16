/*package com.app.ussd.dto;

import java.util.List;

import com.app.ussd.exception.ErrorCodes;

import lombok.Data;

@Data

public class ResponseDTO<T> {
	
	
	private String status;
	private String message;
	private ErrorCodes errorCode;
	private List<String> errors;
	private T response ;
	
	
		
	public ResponseDTO(String status, ErrorCodes errorCode) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		
	}

	public ResponseDTO(String status, T response) {
		super();
		this.status = status;
		this.response = response;
	}

	public ResponseDTO(String status, ErrorCodes errorCode, String message) {
		super();
		this.status = status;
		this.message = message;
		this.errorCode = errorCode;
	}

	public ResponseDTO(String status, ErrorCodes errorCode, List<String> errors) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.errors = errors;
	}
	
	
	
	
	
	

	

}

*/

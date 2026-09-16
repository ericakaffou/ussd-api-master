package com.app.ussd.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.InvalidEntityException;

// Gestionnaire d'execpetion Globale ,


// C'est un l istenner qui ecoute sur toute l'application et  instercepte toutes les exceptions de l'aplication

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorDto> handlerExecpetion (EntityNotFoundException exception , WebRequest webRequest){
		
		final HttpStatus notFound = HttpStatus.NOT_FOUND;
		
		final ErrorDto errorDto = ErrorDto.builder()
		.code(exception.getErrorCode())
		.httpCode(notFound.value())
		.message(exception.getMessage())
		.build();
		
		return new ResponseEntity<ErrorDto>(errorDto, notFound);
		
	}
	
	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorDto> handlerExecpetion (InvalidEntityException exception , WebRequest webRequest){

		final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		
		final ErrorDto errorDto = ErrorDto.builder()
		.code(exception.getErrorCode())
		.httpCode(badRequest.value())
		.message(exception.getMessage())
		.erros(exception.getErrors())
		.build();
		
		return new ResponseEntity<ErrorDto>(errorDto, badRequest);
		
		                                               
	}
	
	@ExceptionHandler(com.app.ussd.exception.Exception.class)
	public ResponseEntity<ErrorDto> handlerExecpetion (com.app.ussd.exception.Exception exception , WebRequest webRequest){

		final HttpStatus badRequest = HttpStatus.OK;
		
		final ErrorDto errorDto = ErrorDto.builder()
		.code(exception.getErrorCode())
		.httpCode(badRequest.value())
		.message(exception.getMessage())		
		.build();
		
		return new ResponseEntity<ErrorDto>(errorDto, badRequest);
		
		                                               
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorDto> handlerExecpetion (BadCredentialsException exception , WebRequest webRequest){

		final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		List<String> msg = new ArrayList<String>();
		msg.add("Login /ou mot de passe incorrect");
		final ErrorDto errorDto = ErrorDto.builder()
		.code(ErrorCodes.BAD_CREDENTIALS)
		.httpCode(badRequest.value())
		.message(exception.getMessage())
		.erros(msg)
		.build();
		
		return new ResponseEntity<ErrorDto>(errorDto, badRequest);
		
		//BAD_REQUEST
		
		                                               
	}
	


}

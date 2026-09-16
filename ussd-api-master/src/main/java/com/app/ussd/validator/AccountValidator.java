package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.AccountDto;

public class AccountValidator {
	
	public static List<String> validate (AccountDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		// Fixed regex for email validation
	    final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		
	/*	if(dto == null) {
			errors.add("Veuillez entrer le nom du compte");
						return errors;
		}  */
		
		if(!StringUtils.hasLength(dto.getAccountName())) {
			errors.add("Veuillez entrer le nom du Compte" );
		}
		
		if(!dto.getEmail().matches(REGEX_EMAIL)) {
			errors.add("Veuillez entrer un email correct" );
		}
			
		
		return errors;
	}


}

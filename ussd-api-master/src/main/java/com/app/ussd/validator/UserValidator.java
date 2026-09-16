package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;


import com.app.ussd.dto.UserDto;

public class UserValidator {
	
 
    
    public static List<String> validate (UserDto dto){
		 
		List<String> errors = new ArrayList<>();
		// Fixed regex for email validation
	    final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		
		
		if(dto == null) {
			errors.add("Veuillez entrer le L'email");
			errors.add("Veuillez entrer le mot de passe");
		//  errors.add("Veuillez entrer le role");
			errors.add("Veuillez entrer le Account");
			
			return errors;
		}
		
		if(!dto.getUsername().matches(REGEX_EMAIL)) {
			errors.add("Veuillez entrer un Username correct (email)" );
		}
		
		if(!StringUtils.hasLength(dto.getUsername())) {
			errors.add("Veuillez entrer le Username" );
		}
		
		if(!StringUtils.hasLength(dto.getPassword())) {
			errors.add("Veuillez entrer le mot de passe" );
		}
		
		// pas de check des roles. Ajout ulterieur
	/*	if(dto.getRoles().isEmpty()) {
			errors.add("Veuillez entrer le Role" );
		}
	*/	
		if(dto.getAccountId() == null) {
			errors.add("Veuillez entrer le Account" );
		}
		
		
		
		return errors;
	}

}

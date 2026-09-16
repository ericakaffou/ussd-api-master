package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.UssdServiceDto;

public class ServiceValidator {

	public static List<String> validate (UssdServiceDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("Veuillez entrer le Nom du Service");
			errors.add("Veuillez entrer le Compte Client");
			return errors;
		}		
		if(!StringUtils.hasLength(dto.getServiceName())) {
			errors.add("Veuillez entrer le Nom du Service" );
		}		
		
		if(dto.getAccountId() == null) {
			errors.add("euillez entrer le Compte Client" );
		}			
		
		return errors;
	}
}

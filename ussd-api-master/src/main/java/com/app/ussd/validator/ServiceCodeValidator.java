package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import com.app.ussd.dto.ServiceCodeDto;

public class ServiceCodeValidator {
	
	public static List<String> validate (ServiceCodeDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("Veuillez entrer le ServiceCode");
			return errors;
		}		
		if(dto.getServiceCode() == null) {
			errors.add("Veuillez entrer le ServiceCode" );
		}	
					
		
		return errors;
	}

}

package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import com.app.ussd.dto.CovrageDto;

public class CovrageValidator {
	
	public static List<String> validate (CovrageDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("Veuillez entrer le Covrage");
			
						return errors;
		}
		
		if(dto.getServiceCodeId() == null) {
			errors.add("Veuillez entrer le serviceCode" );
		}	
			
		
		return errors;
	}

}

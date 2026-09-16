package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.RolesDto;


public class RolesValidator {
	
	  public static List<String> validate (RolesDto dto){
			 
			List<String> errors = new ArrayList<>();
			
			if(dto == null) {
				errors.add("Veuillez entrer le Nom du role");		
				
			return errors;
			}
			
			if(!StringUtils.hasLength(dto.getRoleName())) {
				errors.add("Veuillez entrer le Nom du role" );
			}
			
			
			return errors;
	  }
	  
}

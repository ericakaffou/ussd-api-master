package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.MenuOptionDto;

public class MenuOptionValidator {
	
		
   
	public static List<String> validate (MenuOptionDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("Veuillez entrer le label de l'option");
			errors.add("Veuillez entrer le Numero de l'option");
						return errors;
		}		
		if(!StringUtils.hasLength(dto.getLabel())) {
			errors.add("Veuillez entrer le label de l'option" );
		}		
		
		if(dto.getPostionNumber() == 0 || dto.getPostionNumber() == null ) {
			errors.add("Veuillez entrer le Numero de l'option" );
		}			
		
		return errors;
	}

}

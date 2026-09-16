package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.MenuDto;

public class MenuValidator {
	
	public static List<String> validate (MenuDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("Veuillez entrer le Texte Du Menu");
			errors.add("Veuillez entrer le Type de Menu");
						return errors;
		}		
		if(!StringUtils.hasLength(dto.getText())) {
			errors.add("Veuillez entrer le Texte Du Menu" );
		}		
		
		if(dto.getMenuType() != 0 && dto.getMenuType() != 1 && dto.getMenuType() != 2 ) {
			errors.add("Veuillez entrer un Type Valide" );
		}			
		
		return errors;
	}

}

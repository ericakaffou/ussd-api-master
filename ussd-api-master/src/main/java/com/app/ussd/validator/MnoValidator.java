package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.model.dto.MnoDto;

public class MnoValidator {
	
	
	public static List<String> validate (MnoDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			
			errors.add("mnoName is null");
						return errors;
		}		
		
		
		if(!StringUtils.hasLength(dto.getCountyCode())) {
			errors.add("countyCode is null" );
		}
		if(!StringUtils.hasLength(dto.getMnoName())) {
			errors.add("mnoName is null" );
		}
		
		return errors;
	}

}

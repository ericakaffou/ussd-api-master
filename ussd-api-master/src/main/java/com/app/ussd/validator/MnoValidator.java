package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.MnoDto;

public class MnoValidator {
	
	
	public static List<String> validate (MnoDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("mno.mcc is null");
			errors.add("mno.mnc is null");
			errors.add("mnoName.mcc is null");
						return errors;
		}		
		if(!StringUtils.hasLength(dto.getMcc())) {
			errors.add("mno.mcc is null" );
		}
		
		if(!StringUtils.hasLength(dto.getMnc())) {
			errors.add("mno.mnc is null" );
		}
		
		if(!StringUtils.hasLength(dto.getMnoName())) {
			errors.add("mnoName.mcc is null" );
		}
		
		return errors;
	}

}

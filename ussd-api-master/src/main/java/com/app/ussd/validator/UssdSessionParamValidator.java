package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.UssdSessionParamDto;

public class UssdSessionParamValidator {

	
	public static List<String> validate (UssdSessionParamDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("UssdSessionParam.paramName is null");
			errors.add("UssdSessionParam.paramValue is null");
			errors.add("UssdSessionParam.ussdSession is null");
			return errors;
		}
		
		if(!StringUtils.hasLength(dto.getParamName())) {
			errors.add("UssdSessionParam.paramName is null" );
		}
		if(!StringUtils.hasLength(dto.getParamValue())) {
			errors.add("UssdSessionParam.paramValue is null" );
		}
		
		//if(dto.getUssdSession) {
		//	errors.add("UssdSessionParam.ussdSession is null" );
		//}
				
		return errors;
	}
}

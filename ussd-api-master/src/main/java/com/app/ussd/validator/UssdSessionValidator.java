package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.UssdSessionDto;

public class UssdSessionValidator {
	
	public static List<String> validate (UssdSessionDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("ussdSessionId is null");
			errors.add("ussdSessionMsisdn is null");
			errors.add("ussdSessionStartTime is null");
			errors.add("ussdSessionEndTime is null");
			errors.add("ussdString is null");
			
			return errors;
		}
		
		if(!StringUtils.hasLength(dto.getUssdSessionId())) {
			errors.add("ussdSession.ussdSessionId is null" );
		}
		if(!StringUtils.hasLength(dto.getUssdSessionMsisdn())) {
			errors.add("ussdSession.ussdSessionMsisdn is null" );
		}
		
		if(!StringUtils.hasLength(dto.getUssdSessionStartTime())) {
			errors.add("ussdSession.ussdSessionStartTime is null" );
		}
		
		if(!StringUtils.hasLength(dto.getUssdSessionMsisdn())) {
			errors.add("ussdSession.ussdSessionEndTime is null" );
		}
		
		if(!StringUtils.hasLength(dto.getUssdSessionStartTime())) {
			errors.add("ussdSession.ussdString is null" );
		}
		
		return errors;
	}	

}

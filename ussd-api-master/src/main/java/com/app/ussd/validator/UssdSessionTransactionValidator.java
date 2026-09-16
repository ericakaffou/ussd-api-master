package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.app.ussd.dto.UssdSessionTransactionDto;

public class UssdSessionTransactionValidator {

    
    public static List<String> validate (UssdSessionTransactionDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("UssdSessionTransaction.transactionId is null ");
			errors.add("UssdSessionTransaction.ussdSession is null ");
			return errors;
		}		
		if(!StringUtils.hasLength(dto.getTransactionId())) {
			errors.add("UssdSessionTransaction.transactionId is null " );
		}		
		
		if(dto.getUssdSessionId() == null) {
			errors.add("UssdSessionTransaction.ussdSession is null" );
		}			
		
		return errors;
	}
}

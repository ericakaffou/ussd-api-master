package com.app.ussd.validator;

import java.util.ArrayList;
import java.util.List;

import com.app.ussd.dto.RouteMnoDto;

public class RouteMnoValidator {
	
		
	public static List<String> validate (RouteMnoDto dto){
		 
		List<String> errors = new ArrayList<>();
		
		if(dto == null) {
			errors.add("RouteService.covrage is null");
			errors.add("RouteService.Service is null");
			errors.add("RouteService.mno is null");
			
						return errors;
		}		
		if(dto.getCovrageId() == null) {
			errors.add("RouteService.covrage is null" );
		}	
		
		if(dto.getServiceId() == null) {
			errors.add("RouteService.Service is null" );
		}	
		
		if(dto.getMnoId() == null) {
			errors.add("RouteService.mno is null" );
		}		
		
		return errors;
	}

}

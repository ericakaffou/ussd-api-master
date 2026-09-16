package com.app.ussd.dto;

import com.app.ussd.model.UssdSessionParam;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UssdSessionParamDto {
	
	private Long id;
	
	private String paramName;
	
	private String paramValue;
	
	private UssdSessionDto ussdSessionDto;
	
	public static UssdSessionParamDto fromEntity(UssdSessionParam sessionParam ) {
		   if (sessionParam == null) {
			      return null;
			    }
			    return UssdSessionParamDto.builder()
			    		.id(sessionParam.getId())
			    		.paramName(sessionParam.getParamName())
			    		.paramValue(sessionParam.getParamValue())
			    	//	.ussdSession(sessionParam.getUssdSession())
			    		.build();
		   
	   }
	   
	   public static UssdSessionParam toEntity(UssdSessionParamDto dto) {
		    if (dto == null) {
		      return null;
		    }
		    UssdSessionParam sessionParam = new UssdSessionParam();
		    sessionParam.setId(dto.getId());
		    sessionParam.setParamName(dto.getParamName());
		    sessionParam.setParamValue(dto.getParamValue());
		 // sessionParam.setUssdSession(dto.getUssdSession());		    
		    return sessionParam;
	   }

}

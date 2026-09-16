package com.app.ussd.dto;

import java.util.List;

import com.app.ussd.model.UssdService;
import com.app.ussd.model.UssdSession;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UssdSessionDto {
	
	private Long id;	
	private String ussdSessionId;		
    private String ussdSessionMsisdn;	
    private String ussdSessionStartTime;	
    private String ussdSessionEndTime;	
    private String ussdString;	
	private String statusCode;	
	private Long previousMenuLevel;	
    private Long currentMenuLevel;
    private String mno;    
    private boolean forwarded;
    private UssdService service;   
    
    @JsonIgnore
	private List<UssdSessionParamDto> sessionParamsDto;	
	@JsonIgnore
	private List<UssdSessionTransactionDto> transactionsDto;
	
	 public static UssdSessionDto fromEntity(UssdSession ussdSession ) {
		   if (ussdSession == null) {
			      return null;
			    }
			    return UssdSessionDto.builder()
			    		.id(ussdSession.getId())
			    		.ussdSessionId(ussdSession.getUssdSessionId())
			    		.ussdSessionMsisdn(ussdSession.getUssdSessionMsisdn())
			    		.ussdSessionStartTime(ussdSession.getUssdSessionStartTime())
			    		.ussdSessionEndTime(ussdSession.getUssdSessionEndTime())
			    		.ussdString(ussdSession.getUssdString())
			    		.statusCode(ussdSession.getStatusCode())
			    		.previousMenuLevel(ussdSession.getPreviousMenuLevel())
			    		.currentMenuLevel(ussdSession.getCurrentMenuLevel())
			    		.build();		   
	   }
	   
	   public static UssdSession toEntity(UssdSessionDto dto) {
		    if (dto == null) {
		      return null;
		    }
		    UssdSession ussdSession = new UssdSession();
		    ussdSession.setId(dto.getId());
		    ussdSession.setUssdSessionId(dto.getUssdSessionId());
		    ussdSession.setUssdSessionMsisdn(dto.getUssdSessionMsisdn());
		    ussdSession.setUssdSessionStartTime(dto.getUssdSessionStartTime());
		    ussdSession.setUssdSessionEndTime(dto.getUssdSessionEndTime());
		    ussdSession.setUssdString(dto.getUssdString());
		    ussdSession.setStatusCode(dto.getStatusCode());
		    ussdSession.setPreviousMenuLevel(dto.getPreviousMenuLevel());
		    ussdSession.setCurrentMenuLevel(dto.getCurrentMenuLevel());
		    return ussdSession;
	   }

}

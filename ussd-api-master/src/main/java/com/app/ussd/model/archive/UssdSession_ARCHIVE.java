package com.app.ussd.model.archive;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.app.ussd.dto.UssdSessionDto;
import com.app.ussd.model.AbstractEntity;
import com.app.ussd.model.UssdService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "UssdSession")
public class UssdSession_ARCHIVE extends AbstractEntity {

	
	private String ussdSessionId;		
    private String ussdSessionMsisdn;	
    private String ussdSessionStartTime;	
    private String ussdSessionEndTime;	
    private String ussdString;	
	private String statusCode;	 // ???
	private Long previousMenuLevel;	
    private Long currentMenuLevel;
    private String mno;     // set par l'application backEnd service MNO    
    private boolean forwarded;
    
    
    
    private UssdService service;
    
    @OneToMany(mappedBy = "ussdSession") 
	private List<UssdSessionParam_ARCHIVE> sessionParams;
	
    @OneToMany(mappedBy = "ussdSession") 
	private List<UssdSessionTransaction_ARCHIVE> transactions;
	
 // Mapping de UssdSession -> UssdSessionDto
    public  static UssdSessionDto toDto(UssdSession_ARCHIVE ussdSession) {
    	
    	if (ussdSession == null) {    		
    		return null ;
    		//TODO 
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
    			.mno(ussdSession.getMno())
    			.forwarded(ussdSession.isForwarded())
    			.build() ;
    }
    
 
	  
	  
	
	
    
    
}

package com.app.ussd.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



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
public class UssdSession extends AbstractEntity {

	
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
    
    
    @ManyToOne
    @JoinColumn(name = "service_id")
    private UssdService service;
    
    @OneToMany(mappedBy = "ussdSession") 
	private List<UssdSessionParam> sessionParams;
	
    @OneToMany(mappedBy = "ussdSession") 
	private List<UssdSessionTransaction> transactions;
	

 
	  
	  
	
	
    
    
}

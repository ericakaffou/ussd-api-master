package com.app.ussd.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



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
public class UssdSessionTransaction extends AbstractEntity{
	
	
	
	private String   transactionId;
	
	@ManyToOne
    @JoinColumn(name = "ussdSession_id")
    private UssdSession ussdSession;
	
	private String subscriberInput;
	
	private String statusMessage;  // ????
	
	private Long menuLevel;	
	
	
	
	
	
	}

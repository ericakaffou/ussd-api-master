package com.app.ussd.model.archive;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.ussd.dto.UssdSessionTransactionDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.model.AbstractEntity;
import com.app.ussd.utils.Constants;

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
public class UssdSessionTransaction_ARCHIVE extends AbstractEntity{
	
	
	
	private String   transactionId;
	
	@ManyToOne
    @JoinColumn(name = "ussdSession_id")
    private UssdSession_ARCHIVE ussdSession;
	
	private String subscriberInput;
	
	private String statusMessage;  // ????
	
	private Long menuLevel;	
	
	
	// Mapping de UssdSessionTransaction -> UssdSessionTransactionDto
    public  static UssdSessionTransactionDto toDto(UssdSessionTransaction_ARCHIVE transaction) {
    	
    	if (transaction == null) {    		
    		throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.TRANSACTION_NOT_FOUND);
    	}
    	
    	return UssdSessionTransactionDto.builder()
    			.id(transaction.getId())
    			.transactionId(transaction.getTransactionId())
    			.ussdSessionId(transaction.getUssdSession().getId())	  
    			.subscriberInput(transaction.getSubscriberInput())
    			.statusMessage(transaction.getStatusMessage())
    			.menuLevel(transaction.getMenuLevel())
    			.build() ;
    }
	
	
	}

package com.app.ussd.dto;

import com.app.ussd.model.UssdSessionTransaction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UssdSessionTransactionDto {
	
	
	private Long id;
	
	private String   transactionId;	
	
    private Long ussdSessionId;
	
	private String subscriberInput;
	
	private String statusMessage;
	
	private Long menuLevel;	
	
  //  private long sessionId;
	
	public static UssdSessionTransactionDto fromEntity(UssdSessionTransaction ussdTransaction ) {
		   if (ussdTransaction == null) {
			      return null;
			    }
			    return UssdSessionTransactionDto.builder()
			    		.id(ussdTransaction.getId())
			    		.transactionId(ussdTransaction.getTransactionId())
			    		.ussdSessionId(ussdTransaction.getUssdSession().getId())
			    		.subscriberInput(ussdTransaction.getSubscriberInput())
			    		.statusMessage(ussdTransaction.getStatusMessage())
			    		.menuLevel(ussdTransaction.getMenuLevel())
			    		.build();
		   
	   }
	   
	   public static UssdSessionTransaction toEntity(UssdSessionTransactionDto dto) {
		    if (dto == null) {
		      return null;
		    }
		    UssdSessionTransaction ussdTransaction = new UssdSessionTransaction();
		    ussdTransaction.setId(dto.getId());
		    ussdTransaction.setTransactionId(dto.getTransactionId());
		    //
		    //ussdTransaction.setUssdSession(dto.getUssdSession());
		    
		    ussdTransaction.setSubscriberInput(dto.getSubscriberInput());
		    ussdTransaction.setStatusMessage(dto.getStatusMessage());
		    ussdTransaction.setMenuLevel(dto.getMenuLevel());	    
		    return ussdTransaction;
	   }


}

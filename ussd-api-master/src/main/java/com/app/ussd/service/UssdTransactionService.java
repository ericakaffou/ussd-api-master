package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.ussd.dto.UssdSessionTransactionDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.model.UssdSession;
import com.app.ussd.model.UssdSessionTransaction;
import com.app.ussd.repository.UssdSessionRepository;
import com.app.ussd.repository.UssdTransactionRepository;
import com.app.ussd.utils.Constants;

public class UssdTransactionService {
	

	 private final UssdTransactionRepository transactionRepo; 
     private final UssdSessionRepository ussdSessionRepo;

	
	
	    public UssdTransactionService(UssdTransactionRepository transactionRepo, UssdSessionRepository ussdSessionRepo) {
		super();
		this.transactionRepo = transactionRepo;
		this.ussdSessionRepo = ussdSessionRepo;
	}

	//ListAll
	  public List<UssdSessionTransactionDto> ListAll() {
		  
		  List<UssdSessionTransactionDto> listUssdSessionTransactionDto = new ArrayList<UssdSessionTransactionDto>();
		  List<UssdSessionTransaction> listUssdSessionTransaction = transactionRepo.findAll();
		  if(listUssdSessionTransaction.isEmpty()) {
				throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.NO_USSD_TRANSACTION_FOUND);			
			}		  
		  for( UssdSessionTransaction ussdSessionTransaction : listUssdSessionTransaction ) {
			  listUssdSessionTransactionDto.add(this.toDto(ussdSessionTransaction))  ;			
			}	
		  
		  return listUssdSessionTransactionDto;		   
	  }
	  
	//ListTransactionByUssdSessionId
	  public List<UssdSessionTransactionDto> ListTransactionsByUssdSessionId(long id) {
		  
		  List<UssdSessionTransactionDto> listTransactionDto = new ArrayList<UssdSessionTransactionDto>();
		  
		  Optional<UssdSession> optionalSession = ussdSessionRepo.findById(id);
		  if ( optionalSession.isEmpty() ) {
			   throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.USSD_SESSION_NOT_FOUND,"Service with ID :"+id+"NOT FOUND ");	
		  	  }			  
		     List<UssdSessionTransaction> listTransaction = transactionRepo.findByUssdSessionId(optionalSession.get().getId());
		     
		     for( UssdSessionTransaction transaction : listTransaction ) {
		    	 
		    	 listTransactionDto.add(this.toDto(transaction))  ;			
				}	

		     return listTransactionDto;
	  }
	  
	// Mapping de UssdSessionTransaction -> UssdSessionTransactionDto
	    public   UssdSessionTransactionDto toDto(UssdSessionTransaction transaction) {
	    	
	    	if (transaction == null) {    		
	    		return null ;
	    		// TODO throw an execption
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

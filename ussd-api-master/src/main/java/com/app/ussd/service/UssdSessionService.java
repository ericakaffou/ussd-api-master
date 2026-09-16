package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.ussd.dto.UssdSessionDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.model.UssdService;
import com.app.ussd.model.UssdSession;
import com.app.ussd.model.UssdSessionTransaction;
import com.app.ussd.repository.ServiceRepository;
import com.app.ussd.repository.UssdSessionRepository;
import com.app.ussd.repository.UssdTransactionRepository;
import com.app.ussd.utils.Constants;


@org.springframework.stereotype.Service
public class UssdSessionService {

     private final UssdTransactionRepository transactionRepo; 
     private final UssdSessionRepository ussdSessionRepo;
	 private final ServiceRepository serviceRepo;
	 
	 
	public UssdSessionService(UssdTransactionRepository transactionRepo, UssdSessionRepository ussdSessionRepo,
			ServiceRepository serviceRepo) {
		super();
		this.transactionRepo = transactionRepo;
		this.ussdSessionRepo = ussdSessionRepo;
		this.serviceRepo = serviceRepo;
	}

	//Save
		// cette brique n'enregistre pas les sessions ussd. L'enregistrement est faite par les services operateurs (USSD_BACKEND_MOOV_BN ; USSD_BACKEND_MTN_CG; etc)

	//ListAll
	  public List<UssdSessionDto> FindAll() {
		  
		  List<UssdSessionDto> listUssdSessionDto = new ArrayList<UssdSessionDto>();
		  List<UssdSession> listUssdSession = ussdSessionRepo.findAll();
		  if(listUssdSession.isEmpty()) {
				throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.NO_USSD_SESSION_FOUND);			
			}		  
		  for( UssdSession ussdSession : listUssdSession ) {
			  listUssdSessionDto.add(this.toDto(ussdSession))  ;			
			}	
		  
		  return listUssdSessionDto;		   
	  }
	  
	//ListUssdSessionByServiceId
	  public List<UssdSessionDto> FindUssdSessionsByServiceId(long id) {
		  
		  List<UssdSessionDto> listUssdSessionDto = new ArrayList<UssdSessionDto>();
		  Optional<UssdService> optionalService = serviceRepo.findById(id);
		  if ( optionalService.isEmpty() ) {
			   throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_NOT_FOUND,"Service with ID :"+id+"NOT FOUND ");	
		  	  }			  
		     List<UssdSession> listUssdSession = ussdSessionRepo.findByServiceId(optionalService.get().getId());
		     
		     for( UssdSession ussdSession : listUssdSession ) {
				  listUssdSessionDto.add(this.toDto(ussdSession))  ;			
				}	

		     return listUssdSessionDto;
	  }
    
	//Delete
    public void Delete(long id) {
    	
    	List<UssdSessionTransaction> listTransactions = transactionRepo.findByUssdSessionId(id);
    	if (listTransactions != null) {
			for(UssdSessionTransaction transaction : listTransactions ) {
				transactionRepo.deleteById(transaction.getId());					
			}									
		}			
    	ussdSessionRepo.deleteById(id);
    }
    
    // Mapping de UssdSession -> UssdSessionDto
    public  UssdSessionDto toDto(UssdSession ussdSession) {
    	
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

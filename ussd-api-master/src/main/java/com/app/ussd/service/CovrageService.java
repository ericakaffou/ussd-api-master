/*
Service Standard (CRUD)
*/
package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Covrage;
import com.app.ussd.model.ServiceCode;
import com.app.ussd.model.dto.CovrageDto;
import com.app.ussd.repository.CovrageRepository;
import com.app.ussd.repository.ServiceCodeRepository;
import com.app.ussd.service.CovrageService;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.CovrageValidator;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CovrageService  {
	
	 private final CovrageRepository covrageRepo;
	 private final  ServiceCodeRepository scRepo;
	
	
	
	
	public CovrageService(CovrageRepository covrageRepo, ServiceCodeRepository scRepo) {
		super();
		this.covrageRepo = covrageRepo;
		this.scRepo = scRepo;
		
	}
	//Save
	public CovrageDto Save(CovrageDto dto) throws java.lang.Exception {
		
		List<String> errors = CovrageValidator.validate(dto);	
		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.COVRAGE_NOT_VALID, errors);
		}
		
		/*
		 * REGLES:
		 * Un Sc Dedicated ne peut avoir qu'un seul covrage car il n'est pas partager.
		 */
		 Covrage covrage = new Covrage();
		    covrage.setId(dto.getId());
		    Optional<ServiceCode> optionalServiceCode = scRepo.findById(dto.getServiceCodeId());
		    if(optionalServiceCode.isEmpty()) {	    	
				 throw new EntityNotFoundException(""+Constants.FAILED+" "+ErrorCodes.SERVICE_CODE_NOT_FOUND+", SERVICE_CODE with id = "+ dto.getServiceCodeId() +" Not Found");   
			   }
		   
		    if (optionalServiceCode.get().isDedicated()) {	
		    	
		    	List<Covrage> listCovrage = covrageRepo.findByServiceCodeId(dto.getServiceCodeId());		    	
		    	 if(!listCovrage.isEmpty()) {		    		 
		    		 for (Covrage cov : listCovrage ) {		    			
		    			if(dto.getId() != cov.getId()) {
		    				throw new Exception(""+Constants.FAILED+" "+ErrorCodes.SERVICE_CODE_DEDICATED_ALREDAY_ASSIGNED+", SERVICE_CODE with id = "+ dto.getServiceCodeId() +" Dedicated Already Assigned");   			
		    			}
		    		 }
					 //ex : "SC-122_CC-225_D"
					 covrage.setCovrageName("SC-"+optionalServiceCode.get().getServiceCode()+"_"+"CC-"+optionalServiceCode.get().getCountry().getCountryCode()+"_D");
					 		    		
				 }
		    	
		    }else {   // Not dedicated 
		    	
		    	//findByServiceIdAndChannel
		    	//Check si channel deja Existant
		    	Optional<Covrage> optionalCorvage = covrageRepo.findByServiceCodeIdAndChannel(dto.getServiceCodeId(), dto.getChannel());
		    	if(!optionalCorvage.isEmpty()) {	    	
					 throw new Exception(""+Constants.FAILED+" "+ErrorCodes.COVRAGE_ALREADY_EXIST+"");   
				   }
				//ex : "SC-122_CC-225_1"
				covrage.setCovrageName("SC-"+optionalServiceCode.get().getServiceCode()+"_"+"CC-"+optionalServiceCode.get().getCountry().getCountryCode()+"_"+dto.getChannel()+"");		    	
		    }
		    covrage.setServiceCode(optionalServiceCode.get()); 
	    	covrage.setChannel(dto.getChannel());
		    
		  
		return  this.toDto(covrageRepo.save(covrage));
	}
	//FindById
	public CovrageDto FindById(Long id) {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);
			
		   }
		 	Optional<Covrage> optionalCovrage = covrageRepo.findById(id);
		 	if(optionalCovrage.isEmpty()) {
		 		throw new EntityNotFoundException(Constants.FAILED,ErrorCodes.COVRAGE_NOT_FOUND);
		 	}
		 	return this.toDto(optionalCovrage.get()); 
	}	

	//covrageName
	public CovrageDto covrageName(String covrageName) {
		if (covrageName == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);
			
		   }
		 	Optional<Covrage> optionalCovrage = covrageRepo.findByCovrageName(covrageName);
		 	if(optionalCovrage.isEmpty()) {
		 		throw new EntityNotFoundException(Constants.FAILED,ErrorCodes.COVRAGE_NOT_FOUND);
		 	}
		 	return this.toDto(optionalCovrage.get()); 
	}	
	
	

	//FindByServiceCode
	public  List<CovrageDto> FindByServiceCodeId(Long id) {
		
		List<Covrage> listCovrage = covrageRepo.findByServiceCodeId(id);
		List<CovrageDto> listCovrageDto = new ArrayList<CovrageDto>();
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);
		   }		
		if(listCovrage.isEmpty()) {
			throw new EntityNotFoundException(Constants.FAILED,ErrorCodes.COVRAGE_NOT_FOUND);
		}	
		for( Covrage covrage : listCovrage ) {
			listCovrageDto.add(this.toDto(covrage));			
		}		
		
		return listCovrageDto;
		
	}
	//ListAll
	public List<CovrageDto> FindAll() {
		List<Covrage> listCovrage = covrageRepo.findAll();
		List<CovrageDto> listCovrageDto = new ArrayList<CovrageDto>();		
		if(listCovrage.isEmpty()) {
			throw new EntityNotFoundException(Constants.FAILED,ErrorCodes.COVRAGE_NOT_FOUND);
		}			
		for( Covrage covrage : listCovrage ) {
			listCovrageDto.add(this.toDto(covrage))  ;			
		}		
		return listCovrageDto;
	}
	
	
	//Delete 
	public void Delete(Long id) {
		
		CovrageDto covrageDto = this.FindById(id);	
		
		covrageRepo.deleteById(covrageDto.getId());
	    }
	
	//Mapping de Covrage -> CovrageDto
		 public   CovrageDto toDto(Covrage covrage) {
	    	
	    	if (covrage == null) {    		
	    		throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.COVRAGE_NOT_FOUND);
	    	}    	
	    	return CovrageDto.builder()
	    			.id(covrage.getId())
	    			.serviceCodeId(covrage.getServiceCode().getId())
					.covrageName(covrage.getCovrageName())
	    			.channel(covrage.getChannel())
	    			.build() ;
	    }

		
	

}

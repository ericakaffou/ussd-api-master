package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ussd.dto.ServiceCodeDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Country;
import com.app.ussd.model.ServiceCode;
import com.app.ussd.repository.CountryRepository;
import com.app.ussd.repository.ServiceCodeRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.ServiceCodeValidator;

@Service
public class ServiceCodeService {

	 private final ServiceCodeRepository scRepo;
	
	 private final CountryRepository countryRepo ;
	
	
	
	public ServiceCodeService(ServiceCodeRepository scRepo, CountryRepository countryRepo) {
		super();
		this.scRepo = scRepo;
		this.countryRepo = countryRepo;
	}

	//Save
	public ServiceCodeDto Save(ServiceCodeDto dto) {
		List<String> errors = ServiceCodeValidator.validate(dto);
		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.SERVICE_CODE_NOT_VALID, errors);
		}
		
		Optional<ServiceCode> optionalSC = scRepo.findByServiceCode(dto.getServiceCode());
		if (optionalSC.isPresent()) {
			throw new Exception(Constants.ERROR, ErrorCodes.SERVICE_CODE_ALREADY_EXIST);
		}
		
		// verifier le country_code ici
		Optional<Country> optionalCountry = countryRepo.findByCountryCode(dto.getContry_code());
		if (optionalSC.isEmpty()) {
			throw new Exception(Constants.ERROR, ErrorCodes.PAYS_CODE_NOT_VALID);
		}
		
	 	ServiceCode serviceCode = new ServiceCode();
	    serviceCode.setServiceCode(dto.getServiceCode());
	    serviceCode.setDedicated(dto.isDedicated());
	    serviceCode.setCountry(optionalCountry.get());
		return  this.toDto(scRepo.save(serviceCode));
		    
	}
	
	//findById
	public ServiceCodeDto FindById (Long id) {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
		   }
		
		 Optional<ServiceCode> optionalSc = scRepo.findById(id);
		 if(optionalSc.isEmpty()) {
			 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_CODE_NOT_FOUND);
		 }
		
		 return 	this.toDto(optionalSc.get()); 
	}
	
	//findByServiceCode
		public ServiceCodeDto FindByServiceCode (Long serviceCode) {
			if (serviceCode == null) {
				throw new Exception(Constants.ERROR,ErrorCodes.SERVICE_CODE_NULL);			
			   }			
			 Optional<ServiceCode> optionalSc = scRepo.findByServiceCode(serviceCode);
			 if(optionalSc.isEmpty()) {
				 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_CODE_NOT_FOUND);
			 }
			
			 return 	this.toDto(optionalSc.get()); 
		}
	
	// ListAll
	public List<ServiceCodeDto> FindAll() {
		
		List<ServiceCode> listServiceCode = scRepo.findAll();
		List<ServiceCodeDto> listServiceCodeDto = new ArrayList<ServiceCodeDto>();
		
		if(listServiceCode.isEmpty()) {
			throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_CODE_NOT_FOUND);			
		}			
		for( ServiceCode serviceCode : listServiceCode ) {
			listServiceCodeDto.add(this.toDto(serviceCode))  ;			
		}			
		return listServiceCodeDto;
	}
	

	//delete
	public void Delete(Long id) {
		
		//check and get Route		
		ServiceCodeDto serviceCode = this.FindById(id);				
		scRepo.deleteById(serviceCode.getId());
		
		
	}
	
	
	
	// Mapping de Account -> AccountDto
    public  ServiceCodeDto toDto(ServiceCode serviceCode) {
    	
    	if (serviceCode == null) {    		
    		return null ;
    		// TODO throw an execption
    	}
    	
    	return ServiceCodeDto.builder()
    			.id(serviceCode.getId())
    			.serviceCode(serviceCode.getServiceCode())
    			.dedicated(serviceCode.isDedicated())
    			.build() ;
    }
	
	
}

package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.app.Country;
import com.app.ussd.model.app.ServiceCode;
import com.app.ussd.model.dto.ServiceCodeDto;
import com.app.ussd.repository.CountryRepository;
import com.app.ussd.repository.ServiceCodeRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.ServiceCodeValidator;
import lombok.extern.slf4j.Slf4j;


@Slf4j
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
		// verifier le country_code ici
		Country country = countryRepo.findByCountryCode(dto.getContry_code()).orElseThrow(() -> new Exception(Constants.ERROR, ErrorCodes.PAYS_CODE_NOT_VALID));
				
		Optional<ServiceCode> optionalSC = scRepo.findByServiceCodeAndCountry(dto.getServiceCode() , country );
		if (optionalSC.isPresent()) {
			throw new Exception(Constants.ERROR, ErrorCodes.SERVICE_CODE_ALREADY_EXIST);
		}		
	 	ServiceCode serviceCode = new ServiceCode();
	    serviceCode.setServiceCode(dto.getServiceCode());
	    serviceCode.setDedicated(dto.isDedicated());
	    serviceCode.setCountry(country);
		return  this.toDto(scRepo.save(serviceCode));		    
	}

	//Update
	public ServiceCodeDto Update(ServiceCodeDto dto) {
		if (dto.getId() == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
		   }	
				
		ServiceCode sc = scRepo.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_CODE_NOT_FOUND) );
		List<String> errors = ServiceCodeValidator.validate(dto);
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.SERVICE_CODE_NOT_VALID, errors);
		}
		// verifier le country_code ici
		Country country = countryRepo.findByCountryCode(dto.getContry_code()).orElseThrow(() -> new Exception(Constants.ERROR, ErrorCodes.PAYS_CODE_NOT_VALID));
				
		Optional<ServiceCode> optionalSC = scRepo.findByServiceCodeAndCountryAndIdNot(dto.getServiceCode() , country , dto.getId() );
		if (optionalSC.isPresent()) {
			throw new Exception(Constants.ERROR, ErrorCodes.SERVICE_CODE_ALREADY_EXIST);
		}		
	 	
	    sc.setServiceCode(dto.getServiceCode());
	    sc.setDedicated(dto.isDedicated());
	    sc.setCountry(country);
		return  this.toDto(scRepo.save(sc));		    
	}
	
	//findById
	public ServiceCodeDto FindById (Long id) {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
		   }		
		 ServiceCode sc = scRepo.findById(id).orElseThrow(() -> new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_CODE_NOT_FOUND) );
		 return 	this.toDto(sc); 
	}
	
	//findByServiceCode
		public ServiceCodeDto FindByServiceCode (Long serviceCode) {
			if (serviceCode == null) {
				throw new Exception(Constants.ERROR,ErrorCodes.SERVICE_CODE_NULL);			
			   }	
			ServiceCode sc = scRepo.findByServiceCode(serviceCode).orElseThrow(() -> new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_CODE_NOT_FOUND) );
		 	
			 return 	this.toDto(sc); 
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
    		throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_CODE_NOT_FOUND);
    	}    	
    	return ServiceCodeDto.builder()
    			.id(serviceCode.getId())
    			.serviceCode(serviceCode.getServiceCode())
    			.dedicated(serviceCode.isDedicated())
				.contry_code(serviceCode.getCountry().getCountryCode())
    			.build() ;
    }
	
	
}

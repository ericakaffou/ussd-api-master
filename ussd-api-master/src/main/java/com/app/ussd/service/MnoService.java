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
import com.app.ussd.model.app.Country;
import com.app.ussd.model.app.Mno;
import com.app.ussd.model.dto.MnoDto;
import com.app.ussd.repository.CountryRepository;
import com.app.ussd.repository.MnoRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.MnoValidator;

@Service
public class MnoService {
	

	 private final MnoRepository mnoRepo;
	private final CountryRepository countryRepo;
	 
	 
			
	public MnoService(MnoRepository mnoRepo, CountryRepository countryRepo) {
		super();
		this.mnoRepo = mnoRepo;
		this.countryRepo = countryRepo;
		
	}

	//Save
	public MnoDto Save(MnoDto dto) {
		List<String> errors = MnoValidator.validate(dto);		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.MNO_NOT_VALID, errors);
		}

		Country country = countryRepo.findByCountryCode(dto.getCountyCode()).orElseThrow(() ->  new Exception (Constants.ERROR,ErrorCodes.COUNRTY_NOT_FOUND));

		Optional <Mno> optionalMno = mnoRepo.findByMnoNameAndCountry(dto.getMnoName() , country) ;
		if(optionalMno.isPresent()){
			  throw new Exception (Constants.ERROR,ErrorCodes.MNO_ALREADY_EXIST);
			  } 
			
		
		 Mno scRegMno = new Mno();
		    scRegMno.setCountry(country);
		 	scRegMno.setMnoName(dto.getMnoName());
		    return  this.toDto(mnoRepo.save(scRegMno));		    
	}	

	//update
	public MnoDto Update(MnoDto dto) {

		Mno mno = mnoRepo.findById(dto.getId()).orElseThrow(() -> new Exception(Constants.ERROR,ErrorCodes.MNO_NOT_FOUND));
					
		List<String> errors = MnoValidator.validate(dto);		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.MNO_NOT_VALID, errors);
		}

		Country country = countryRepo.findByCountryCode(dto.getCountyCode()).orElseThrow(() ->  new Exception (Constants.ERROR,ErrorCodes.COUNRTY_NOT_FOUND));

		
		// exclure le mno en court
		Optional <Mno> checkMno = mnoRepo.findByMnoNameAndCountryAndIdNot(dto.getMnoName() , country , dto.getId()) ;
		if(checkMno.isPresent()){
			  throw new Exception (Constants.ERROR,ErrorCodes.MNO_ALREADY_EXIST);
			  } 
			  
		
		    mno.setCountry(country);
		 	mno.setMnoName(dto.getMnoName());
		    return  this.toDto(mnoRepo.save(mno));		    
	}	
	
	//FindById
	public MnoDto FindById(Long id)  {
			if (id == null) {
				throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
			   }
			 	Mno mno = mnoRepo.findById(id).orElseThrow(() -> new EntityNotFoundException (Constants.FAILED,ErrorCodes.MNO_NOT_FOUND));	
			 	 	
			 	
			 	return 	this.toDto(mno); 
				
		}
	
			
		//FindAll
		public List<MnoDto> FindAll() {
			List<Mno> listMno = mnoRepo.findAll();
			List<MnoDto> listMnoDto = new ArrayList<MnoDto>();		
			if(!listMnoDto.isEmpty()) {
				throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MNO_NOT_FOUND);			
			}			
			for( Mno mno : listMno ) {
				listMnoDto.add(this.toDto(mno))  ;			
			}			
			return listMnoDto;
		}

		
		//Delete
		public void Delete(Long id) {
			
			MnoDto mno = this.FindById(id);
					    	    	
		    	mnoRepo.deleteById(mno.getId());	
		    	
				    		     		
		}


		// Mapping de Mno -> MnoDto
		    public   MnoDto toDto(Mno mno) {
		    	
		    	if (mno == null) { 
					throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MNO_NOT_FOUND);  	   		
		    		
		    	}
		    	
		    	return MnoDto.builder()
		    			.id(mno.getId())		    			
		    			.mnoName(mno.getMnoName())
						.countyCode(mno.getCountry().getCountryCode())
		    			.build() ;
		    }
}

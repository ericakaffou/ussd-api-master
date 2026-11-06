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
		
		Optional <Mno> optionalMno = mnoRepo.findByMnoNameAndCountry(dto.getMnoName() , dto.getCountyCode()) ;
		if(!optionalMno.isEmpty()){
			  throw new Exception (Constants.ERROR,ErrorCodes.MNO_ALREADY_EXIST);
			  } 
		Optional <Country> optionalcountry = countryRepo.findByCountryCode(dto.getCountyCode()) ;
		if(!optionalcountry.isEmpty()){
			  throw new Exception (Constants.ERROR,ErrorCodes.COUNRTY_NOT_FOUND);
			  } 
		
		
		 Mno scRegMno = new Mno();
		    scRegMno.setCountry(optionalcountry.get());
		 	scRegMno.setMnoName(dto.getMnoName());
		    return  this.toDto(mnoRepo.save(scRegMno));		    
	}	
	
	//FindById
	public MnoDto FindById(Long id)  {
			if (id == null) {
				throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
			   }
			 	Optional<Mno> optionalMno = mnoRepo.findById(id);	
			 	
			 	if(optionalMno.isEmpty()) {
					 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MNO_NOT_FOUND);
				}
			 	
			 	return 	this.toDto(optionalMno.get()); 
				
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

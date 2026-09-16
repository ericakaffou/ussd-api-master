package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ussd.dto.MnoDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Mno;
import com.app.ussd.model.RouteMno;
import com.app.ussd.repository.MnoRepository;
import com.app.ussd.repository.RouteMnoRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.MnoValidator;

@Service
public class MnoService {
	

	 private final MnoRepository mnoRepo;
	 private final RouteMnoRepository routeMnoRepo;
	 
	 
			
	public MnoService(MnoRepository mnoRepo, RouteMnoRepository routeMnoRepo) {
		super();
		this.mnoRepo = mnoRepo;
		this.routeMnoRepo = routeMnoRepo;
	}

	//Save
	public MnoDto Save(MnoDto dto) {
		List<String> errors = MnoValidator.validate(dto);		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.MNO_NOT_VALID, errors);
		}
		
		Optional <Mno> optionalMno = mnoRepo.findByMccAndMncAndMnoName(dto.getMcc(), dto.getMnc(), dto.getMnoName()) ;
		if(!optionalMno.isEmpty()){
			  throw new Exception (Constants.ERROR,ErrorCodes.MNO_ALREADY_EXIST);
			  } 
		
		 Mno scRegMno = new Mno();
		    scRegMno.setId(dto.getId());
		    scRegMno.setMnoName(dto.getMnoName());
		    scRegMno.setMnc(dto.getMnc());
		    scRegMno.setMcc(dto.getMcc());
			return  Mno.toDto(mnoRepo.save(scRegMno));		    
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
			 	
			 	return 	Mno.toDto(optionalMno.get()); 
				
		}
	
			
		//FindAll
		public List<MnoDto> FindAll() {
			List<Mno> listMno = mnoRepo.findAll();
			List<MnoDto> listMnoDto = new ArrayList<MnoDto>();		
			if(!listMnoDto.isEmpty()) {
				throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MNO_NOT_FOUND);			
			}			
			for( Mno mno : listMno ) {
				listMnoDto.add(Mno.toDto(mno))  ;			
			}			
			return listMnoDto;
		}

		
		//Delete
		public void Delete(Long id) {
			
			MnoDto mno = this.FindById(id);
			
	    	    List<RouteMno> listMno = routeMnoRepo.findByMnoId(mno.getId());    	 
	    	   	if (!listMno.isEmpty()) {
		    		throw new Exception(Constants.FAILED,ErrorCodes.ROUTE_MNO_ALREADY_EXIST, "Delete All ROUTE_MNO linked before");
				}
	    	   			    	    	
		    	mnoRepo.deleteById(mno.getId());	
		    	
				    		     		
		}
}

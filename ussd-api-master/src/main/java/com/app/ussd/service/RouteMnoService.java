package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ussd.dto.RouteMnoDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Covrage;
import com.app.ussd.model.Mno;
import com.app.ussd.model.RouteMno;
import com.app.ussd.model.UssdService;
import com.app.ussd.repository.CovrageRepository;
import com.app.ussd.repository.MnoRepository;
import com.app.ussd.repository.RouteMnoRepository;
import com.app.ussd.repository.ServiceRepository;
import com.app.ussd.service.RouteMnoService;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.RouteMnoValidator;

@Service
public class RouteMnoService  {
	
	 private final RouteMnoRepository routeMnoRepo;
	 private final CovrageRepository covrageRepo;
	 private final ServiceRepository serviceRepo;
	 private final MnoRepository mnoRepo;
	
	 
	
	public RouteMnoService(RouteMnoRepository routeMnoRepo, CovrageRepository covrageRepo,
			ServiceRepository serviceRepo, MnoRepository mnoRepo) {
		super();
		this.routeMnoRepo = routeMnoRepo;
		this.covrageRepo = covrageRepo;
		this.serviceRepo = serviceRepo;
		this.mnoRepo = mnoRepo;
	}


	//Save
	public RouteMnoDto Save(RouteMnoDto dto) {
		List<String> errors = RouteMnoValidator.validate(dto);
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.ROUTE_MNO_NOT_VALID, errors);
		}
		
		//check and get Coverage		
	       Optional<Covrage> optionalCovrage = covrageRepo.findById(dto.getCovrageId());		
			if(optionalCovrage.isEmpty()) {	
		 		throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.COVRAGE_NOT_FOUND," Covrage with id = "+ dto.getCovrageId() +"Not Found");		
			}
		
			//check and get Service		
		       Optional<UssdService> optionalService = serviceRepo.findById(dto.getServiceId());		
				if(optionalService.isEmpty()) {	
			 		throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_NOT_FOUND," Service with id = "+ dto.getServiceId() +"Not Found");		
				}
				
				//check and get mNO		
			       Optional<Mno> optionalMno = mnoRepo.findById(dto.getMnoId());		
					if(optionalMno.isEmpty()) {	
				 		throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MNO_NOT_FOUND," Mno with id = "+ dto.getMnoId() +"Not Found");		
					}	
					
		//Check exiting of old same route
		Optional<RouteMno> optionalOldRoute = routeMnoRepo.findByCovrageIdAndServiceIdAndMnoId(dto.getCovrageId(), dto.getServiceId(), dto.getMnoId());
		
		if(!optionalOldRoute.isEmpty()) {	
			throw new Exception(Constants.FAILED,ErrorCodes.ROUTE_MNO_ALREADY_EXIST);
	 	
			}	
			
		RouteMno route = new RouteMno();
		route.setId(dto.getId());
		route.setCovrage(optionalCovrage.get());
		route.setService(optionalService.get());
		route.setCallBackUrl(dto.getCallBackUrl());
		route.setMno(optionalMno.get());
		
		
		return this.toDto(routeMnoRepo.save(route));
	}	


	// FindById
			public RouteMnoDto FindById(Long id) {

				if (id == null) {
					throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);
				   }					
				Optional<RouteMno> optionalRoute = routeMnoRepo.findById(id);
				if ( optionalRoute.isEmpty() ) {
					  throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.ROUTE_MNO_NOT_FOUND);			  
				  }
			 	return this.toDto(optionalRoute.get()); 	
			}

		
	

	// get route by covrage
	public List<RouteMnoDto> FindByCoverageId(Long id) {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);
		   }
		List<RouteMno> listRouteMno = routeMnoRepo.findByCovrageId(id);
		if(listRouteMno.isEmpty()) {
			throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.ROUTE_MNO_NOT_FOUND);
			}	
		List<RouteMnoDto> listRouteMnoDto = new ArrayList<RouteMnoDto>();
		
		for( RouteMno routeMno : listRouteMno ) {
			listRouteMnoDto.add(this.toDto(routeMno))  ;			
		}		
		return listRouteMnoDto;		
	}
	//listAll
	public  List<RouteMnoDto> findAll() {
		
		List<RouteMno> listRouteMno = routeMnoRepo.findAll();
		List<RouteMnoDto> listRouteMnoDto = new ArrayList<RouteMnoDto>();
		
		for( RouteMno routeMno : listRouteMno ) {
			listRouteMnoDto.add(this.toDto(routeMno))  ;			
		}
		return listRouteMnoDto;
			
	}
	//delete
	public void Delete(Long id) {		
		//check and get Route		
		RouteMnoDto routeMno = this.FindById(id);				
		routeMnoRepo.deleteById(routeMno.getId());		
	}
	
	// Mapping de RouteMno -> RouteMnoDto
    public  RouteMnoDto toDto(RouteMno route) {
    	
    	if (route == null) {    		
    		return null ;
    		// TODO throw an execption
    	}
    	
    	return RouteMnoDto.builder()
    			.id(route.getId())
    			.covrageId(route.getCovrage().getId())
    			.callBackUrl(route.getCallBackUrl())	
    			.serviceId(route.getService().getId())
    			.mnoId(route.getMno().getId())
    			.build() ;
    }
	

}

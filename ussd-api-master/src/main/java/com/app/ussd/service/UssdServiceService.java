package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ussd.dto.UssdServiceDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Account;
import com.app.ussd.model.Menu;
import com.app.ussd.model.UssdService;
import com.app.ussd.model.UssdSession;
import com.app.ussd.repository.AccountRepository;
import com.app.ussd.repository.MenuRepository;
import com.app.ussd.repository.ServiceRepository;
import com.app.ussd.repository.UssdSessionRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.ServiceValidator;



@Service
public class UssdServiceService {
	
	 private final MenuRepository menuRepo;
	 private final ServiceRepository serviceRepo;
	 private final UssdSessionRepository sessionRepo;
	 private final AccountRepository accountRepo;	
	 private final MenuService menuService;
	 private final UssdSessionService ussdSessionService;
	
	
	
	public UssdServiceService(MenuRepository menuRepo, ServiceRepository serviceRepo, UssdSessionRepository sessionRepo,
			AccountRepository accountRepo, MenuService menuService, UssdSessionService ussdSessionService) {
		super();
		this.menuRepo = menuRepo;
		this.serviceRepo = serviceRepo;
		this.sessionRepo = sessionRepo;
		this.accountRepo = accountRepo;
		this.menuService = menuService;
		this.ussdSessionService = ussdSessionService;
	}

	//Save
	public UssdServiceDto Save(UssdServiceDto dto) throws java.lang.Exception {
		List<String> errors = ServiceValidator.validate(dto);
		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.SERVICE_NOT_VALID, errors);
		}
		// check if Accountd is ok
		Optional<Account> optionalAccount = accountRepo.findById(dto.getAccountId());	
	 	
	 	if(optionalAccount.isEmpty()) {
			 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.ACCOUNT_NOT_FOUND);
		}
	 	
	 	
	    // check if Service already exist 
	 	Optional<UssdService> optionalUssdService = serviceRepo.findByServiceNameAndAccount(dto.getServiceName(), optionalAccount.get() );
	 	if(!optionalUssdService.isEmpty()) {
			 throw new Exception (Constants.ERROR,ErrorCodes.SERVICE_ALREADY_EXIST);
		}
	 	
		UssdService service = new UssdService();
		service.setId(dto.getId()); // Tres important pour la modification
		service.setAccount(optionalAccount.get());
		service.setServiceName(dto.getServiceName());
		service.setForwarded(dto.isForwarded());
		service.setEnabled(dto.isEnabled());
		
		return  this.toDto(serviceRepo.save(service));
		
		
		    
	}
	
	//FindById
	public UssdServiceDto FindById( Long id) {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
		   }
		
		 Optional<UssdService> optionalService = serviceRepo.findById(id);
		 if(optionalService.isEmpty()) {
			 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_NOT_FOUND);
		 } 
				 return 	this.toDto(optionalService.get()); 
	}

	
	//FindByAccountId
	public List<UssdServiceDto> FindByAccountId(Long id) {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
		   }		
		List<UssdService> listUssdService = serviceRepo.findByAccountId(id);
		List<UssdServiceDto> listUssdServiceDto = new ArrayList<UssdServiceDto>();
		
		if(listUssdService.isEmpty()) {
			throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_NOT_FOUND);			
		}			
		for( UssdService ussdService : listUssdService ) {
			listUssdServiceDto.add(this.toDto(ussdService))  ;			
		}			
		return listUssdServiceDto;
		
	}

	//FindAll
	 public List<UssdServiceDto> FindAll() {
		 
		 List<UssdService> listUssdService = serviceRepo.findAll();
		 List<UssdServiceDto> listUssdServiceDto = new ArrayList<UssdServiceDto>();
			
			if(listUssdService.isEmpty()) {
				throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_NOT_FOUND);			
			}			
			for( UssdService ussdService : listUssdService ) {
				listUssdServiceDto.add(this.toDto(ussdService))  ;			
			}			
			return listUssdServiceDto;
	 }
	
	
	//Delete
	 //La suppression ne doit pas etre possible car cela entrainnera des erreur si les données associées ne sont pas supprimées.
	 // l'ideal c'est de desactiver le service avec la propiété enabled
	public void Delete(long serviceId) throws java.lang.Exception {
		
		List<Menu> listMenu = menuRepo.findByServiceId(serviceId);
		if (listMenu != null) {
			for(Menu menu : listMenu ) {
				menuService.Delete(menu.getId());
			}				    
		}
		
		List<UssdSession> listSession = sessionRepo.findByServiceId(serviceId);
		
		if (listSession != null) {
			for(UssdSession session : listSession ) {
				ussdSessionService.Delete(session.getId());
			}				    
		}
		
		serviceRepo.deleteById(serviceId);
		
	}
	
	// Mapping de Account -> AccountDto
    public  UssdServiceDto toDto(UssdService ussdService) {
    	
    	if (ussdService == null) {    		
    		return null ;
    		// TODO throw an execption
    	}
    	
    	return UssdServiceDto.builder()
    			.id(ussdService.getId())
    			.serviceName(ussdService.getServiceName())
    			.accountId(ussdService.getAccount().getId())	
    			.forwarded(ussdService.isForwarded())
    			.enabled(ussdService.isEnabled())
    			.build() ;
    }


}

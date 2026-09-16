package com.app.ussd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import com.app.ussd.dto.AccountDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.exception.Exception;
import com.app.ussd.model.Account;
import com.app.ussd.model.Roles;
import com.app.ussd.model.UssdService;
import com.app.ussd.model.Utilisateur;
import com.app.ussd.repository.AccountRepository;
import com.app.ussd.repository.ServiceRepository;
import com.app.ussd.repository.UtilisateurRepository;
import com.app.ussd.service.AccountService;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.AccountValidator;



@org.springframework.stereotype.Service
public class AccountService  {
	
	
	private final AccountRepository accountRepo;
	
	private final UtilisateurRepository userRepo;
	
	private  final ServiceRepository serviceRepo;
	
	private final RoleService roleService;
	
	
		
	public AccountService(AccountRepository accountRepo, UtilisateurRepository userRepo, ServiceRepository serviceRepo,
			RoleService roleService) {
		super();
		this.accountRepo = accountRepo;
		this.userRepo = userRepo;
		this.serviceRepo = serviceRepo;
		this.roleService = roleService;
	}





	//Save &Edit
	public AccountDto Save(AccountDto dto) {
		
		List<String> errors = AccountValidator.validate(dto);
		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.ACCOUNT_NOT_VALID, errors);
		}
		
		 Optional<Account> optionalAccount = accountRepo.findByAccountName(dto.getAccountName());
	      if( optionalAccount.isPresent()){
			  throw new Exception (Constants.FAILED,ErrorCodes.ACCOUNT_ALREADY_EXIST);
			  } 
		
		
		 Optional<Utilisateur> optionalUser = userRepo.findByUsername(dto.getEmail());
	      if( optionalUser.isPresent()){
			  throw new Exception (Constants.FAILED,ErrorCodes.EMAIL_ALREADY_EXIST);
			  } 
	      
		Account account = new Account();
	    account.setId(dto.getId());
	    account.setAccountName(dto.getAccountName());
	    account.setEmail(dto.getEmail());
	    account.setEnabled(dto.isEnabled());
	    
	    
	    AccountDto savedAccount = this.toDto(accountRepo.save(account));
	    
	      Utilisateur newUser = new Utilisateur();
		  newUser.setAccount(account);
		  newUser.setUsername(account.getEmail());
		  //Encodage du mot de passe 
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		  String passwordHash = encoder.encode(dto.getPassword());
		  newUser.setPassword(passwordHash);		 
		  
		  // Add role ADMIN to NEW User  
		  Set<Roles> roles = new HashSet<>();		  
		  Roles role = roleService.findByName("ADMIN");
		  roles.add(role);				
		  newUser.setRoles(roles);
		  newUser.setEnabled(dto.isEnabled());	
		  userRepo.save(newUser);
		  
		  
		  return  savedAccount;
		   
	}	
	
	
	
	
	
	//FindById
	public AccountDto FindById(Long id)  {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
		   }
		 	Optional<Account> optionalAccount = accountRepo.findById(id);	
		 	
		 	if(optionalAccount.isEmpty()) {
				 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.ACCOUNT_NOT_FOUND);
			}
		 	
		 	return  this.toDto(optionalAccount.get()); 
			
	}
	
	//FindByAccountName
	public AccountDto FindByAccountName(String accountName) {
		if(!StringUtils.hasLength(accountName)) {
			throw new Exception (Constants.ERROR,ErrorCodes.ACCOUNT_NAME_NULL);			   
		   }
		 	Optional<Account> optionalAccount = accountRepo.findByAccountName(accountName);		 	
		 	if (optionalAccount.isEmpty()) {
		 		throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.ACCOUNT_NOT_FOUND);
		 	}
		 	return  this.toDto(optionalAccount.get()); 
	}
	
	//FindAll
	public List<AccountDto> FindAll() {
		List<Account> listAccount = accountRepo.findAll();
		List<AccountDto> listAccountDto = new ArrayList<AccountDto>();		
		if(listAccount.isEmpty()) {
			throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.ACCOUNT_NOT_FOUND);			
		}			
		for( Account account : listAccount ) {
			listAccountDto.add(this.toDto(account))  ;			
		}			
		return listAccountDto;
	}
	
	//Delete
	// Un compte ne devrait pas etre supprimer; utiliser plutot l'option enabled
	// TODO suppression en cascade
	public void Delete(Long id) {
		
		AccountDto account = this.FindById(id);
		
    	    List<Utilisateur> listUsers = userRepo.findByAccountId(account.getId());    	 
    	   	if (!listUsers.isEmpty()) {
	    		throw new Exception(Constants.FAILED,ErrorCodes.USER_EXIST, "Delete All USER linked before");
			}
    	   	
	    	List<UssdService> listServices = serviceRepo.findByAccountId(id);
	    	if (!listServices.isEmpty()) {
	    		throw new Exception(Constants.FAILED,ErrorCodes.SERVICE_ALREADY_EXIST, "Delete All SERVICE linked before");								
			}	    	
	    	accountRepo.deleteById(account.getId());	
	    	
			    		     		
	}
	
	// Mapping de Account -> AccountDto
    public   AccountDto toDto(Account account) {
    	
    	if (account == null) {    		
    		return null ;
    		// TODO throw an execption
    	}
    	
    	return AccountDto.builder()
    			.id(account.getId())
    			.accountName(account.getAccountName())
    			.email(account.getEmail())
    			.enabled(account.isEnabled())	    			
    			.build() ;
    }
	

	
	
	
}



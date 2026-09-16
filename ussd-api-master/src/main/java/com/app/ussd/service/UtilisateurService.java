package com.app.ussd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.ussd.dto.UserDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Account;
import com.app.ussd.model.Roles;
import com.app.ussd.model.Utilisateur;
import com.app.ussd.repository.AccountRepository;
import com.app.ussd.repository.RoleRepository;
import com.app.ussd.repository.UtilisateurRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.UserValidator;


@Service
public class UtilisateurService {
	
	 private final UtilisateurRepository userRepo;
	 private final AccountRepository accountRepo;
	 private final RoleRepository roleRepo;

	
	public UtilisateurService(UtilisateurRepository userRepo, AccountRepository accountRepo, RoleRepository roleRepo) {
		super();
		this.userRepo = userRepo;
		this.accountRepo = accountRepo;
		this.roleRepo = roleRepo;
	}
	//Save TODO
	public UserDto Save(UserDto dto) throws java.lang.Exception {
		List<String> errors = UserValidator.validate(dto);
		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.USER_NOT_VALID, errors);
		}
		
		 Optional<Account> optionalAcount = accountRepo.findById(dto.getAccountId());
		 if( !optionalAcount.isPresent()){
			  throw new InvalidEntityException (Constants.ERROR,ErrorCodes.ACCOUNT_NOT_FOUND);
			  }  
		 
		 Optional<Utilisateur> optionalUser = userRepo.findByUsername(dto.getUsername());
	      if( optionalUser.isPresent()){
			  throw new Exception (Constants.ERROR,ErrorCodes.USER_EXIST);
			  } 
		
	      Utilisateur newUser = new Utilisateur();
		  newUser.setAccount(optionalAcount.get());
		  newUser.setUsername(dto.getUsername());
		  //Encodage du mot de passe 
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		  String passwordHash = encoder.encode(dto.getPassword());
		  newUser.setPassword(passwordHash);
		  newUser.setEnabled(dto.isEnabled());
		  
		
		  // Add role to User  
		  Set<Roles> roles = new HashSet<>();
		  for( String  rol : dto.getRoles() ) {
			 Optional <Roles> optionalRole = roleRepo.findByRoleName(rol);
			 if( optionalRole.isEmpty()){
			  throw new InvalidEntityException (Constants.ERROR,ErrorCodes.ROLE_NOT_VALID);
			  }			 
			  roles.add(optionalRole.get());		
			}
		  newUser.setRoles(roles);	
		  
		  		  
		  
		  
		  return  this.toDto(userRepo.save(newUser));	
		
		    
	}
	//FindById
	public UserDto FindById(Long id)  {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
		   }
		 	Optional<Utilisateur> optionalUser = userRepo.findById(id);		 	
		 	if(optionalUser.isEmpty()) {
				 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.USER_NOT_FOUND);
			}		 	
		 	return 	this.toDto(optionalUser.get()); 			
	}
	
	//FindByUserName
		public UserDto FindByUserName(String userName)  {
			if(!StringUtils.hasLength(userName)) {			
				throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
			   }
			 	Optional<Utilisateur> optionalUser = userRepo.findByUsername(userName);		 	
			 	if(optionalUser.isEmpty()) {
					 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.USER_NOT_FOUND);
				}		 	
			 	return 	this.toDto(optionalUser.get()); 			
		}
	
	//FindByAccountId
	public List<UserDto> FindByAccountId(Long id)  {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);			
		   }
			List<UserDto> listUserDto = new ArrayList<UserDto>();
		 	List<Utilisateur> listUser = userRepo.findByAccountId(id)	;	 	
		 	if(listUser.isEmpty()) {
				 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.USER_NOT_FOUND);
			}		 	
		 	for( Utilisateur utilisateur : listUser ) {
				listUserDto.add(this.toDto(utilisateur))  ;			
			}			
			return listUserDto;
		
	}
	
	
	//listAll
	public List<UserDto> FindAll() {
		List<Utilisateur> listUser = userRepo.findAll();
		List<UserDto> listUserDto = new ArrayList<UserDto>();		
		if(listUser.isEmpty()) {
			throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.NO_USER_FOUND);			
		}			
		for( Utilisateur utilisateur : listUser ) {
			listUserDto.add(this.toDto(utilisateur))  ;			
		}			
		return listUserDto;
	}
	
	//Delete	
	public void Delete(Long id)  {		 
		 UserDto user = this.FindById(id);
		 userRepo.deleteById(user.getId());		 
		 
	 }
	
	// Mapping de User -> UserDto
    public  UserDto toDto(Utilisateur utilisateur) {
    	
    	if (utilisateur == null) {    		
    		return null ;
    		// TODO throw an execption
    	}
    	
    /*	 List<Roles> roles = new ArrayList<>();
    	 List<Roles> userRoles = userRepo.getUserRoles(utilisateur.getId());
    	 for( Roles role : userRoles ) {
    		 roles.add(role)  ;			
 		}	
 		*/			   
		   
    	return UserDto.builder()
    			.id(utilisateur.getId())
    			.username(utilisateur.getUsername())
    			.enabled(utilisateur.isEnabled())
    			.accountId(utilisateur.getAccount().getId())
    			//.roles(roles)
    			.build() ;
    }
    
	
	

}

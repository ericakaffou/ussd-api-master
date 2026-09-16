package com.app.ussd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ussd.dto.RolesDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Roles;
import com.app.ussd.repository.RoleRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.RolesValidator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class RoleService {
	
	 private final RoleRepository roleRepo;
	
	
	
	public RoleService(RoleRepository roleRepo) {
		super();
		this.roleRepo = roleRepo;
	}

	public RolesDto save (RolesDto dto) throws java.lang.Exception {
		
		List<String> errors = RolesValidator.validate(dto);
		
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.ROLE_NOT_VALID, errors);
		}
		
		 
		 Optional<Roles> optionalRole = roleRepo.findByRoleName(dto.getRoleName());
		 if( optionalRole.isPresent()){
			 
			throw new Exception(Constants.ERROR, ErrorCodes.ROLE_ALREADY_EXIST);
			  } 
		Roles role = new Roles();
		role.setRoleName(dto.getRoleName());
		
		return this.toDto(roleRepo.save(role));
		
		
		
	}
	
	public Roles findByName(String roleName) {		
		
		Optional<Roles> optionalRole = roleRepo.findByRoleName(roleName);	
	 	
	 	if(optionalRole.isEmpty()) {
			 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.ROLE_NOT_FOUND);
		}
	 	
	 	return optionalRole.get();
}
	
	
	// Mapping de Account -> AccountDto
	public   RolesDto toDto(Roles role) {
	
	if (role == null) {    		
		return null ;
		// TODO throw an execption
	}
	
	return RolesDto.builder()
			.id(role.getId())
			.roleName(role.getRoleName())    				    			
			.build() ;
	}


}

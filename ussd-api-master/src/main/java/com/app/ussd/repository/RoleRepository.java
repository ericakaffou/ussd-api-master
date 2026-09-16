package com.app.ussd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.Roles;


public interface RoleRepository  extends JpaRepository<Roles, Long>{
	
	Optional<Roles> findByRoleName(String roleName); 

}

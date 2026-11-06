package com.app.ussd.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ussd.model.app.Account;



public interface AccountRepository extends JpaRepository<Account, Long>{
	
	 Optional<Account> findByAccountName (String accountName);	

	  Optional<Account> findByAccountNameAndIdNot (String accountName ,Long id);	
	
	
}

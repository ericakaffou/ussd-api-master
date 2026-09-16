package com.app.ussd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.Account;




public interface AccountRepository extends JpaRepository<Account, Long>{
	
	public Optional<Account> findByAccountName (String accountName);
	
	
	
	
}

package com.app.ussd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.Account;
import com.app.ussd.model.UssdService;



public interface ServiceRepository extends JpaRepository<UssdService, Long> {

		public List<UssdService> findByAccountId(long accountId);
		
		public Optional<UssdService> findByServiceNameAndAccount(String serviceName, Account account);
	
	
	
	
}



package com.app.ussd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.UssdSessionTransaction;

public interface UssdTransactionRepository extends JpaRepository<UssdSessionTransaction, Long>{
	
	public List<UssdSessionTransaction> findByUssdSessionId(Long id);

}

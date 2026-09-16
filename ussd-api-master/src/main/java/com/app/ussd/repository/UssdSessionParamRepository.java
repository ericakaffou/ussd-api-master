package com.app.ussd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.UssdSession;
import com.app.ussd.model.UssdSessionParam;


public interface UssdSessionParamRepository extends JpaRepository<UssdSessionParam, Long>{
      
	public List<UssdSessionParam> findByUssdSession(UssdSession session);
	
	public Optional<UssdSessionParam> findByparamName(String paramName);
	
	
}

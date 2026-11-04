package com.app.ussd.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ussd.model.app.Mno;

public interface MnoRepository  extends JpaRepository<Mno, Long> {
	
	public Optional <Mno> findByMnoNameAndCountry(String mnoName  , String  countryCode) ;

}

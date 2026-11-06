package com.app.ussd.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ussd.model.app.Country;
import com.app.ussd.model.app.Mno;

public interface MnoRepository  extends JpaRepository<Mno, Long> {
	
	 Optional <Mno> findByMnoNameAndCountry(String mnoName  , Country  country) ;

	 Optional<Mno> findByMnoNameAndCountryAndIdNot( String mnoName, Country country,Long id);

}

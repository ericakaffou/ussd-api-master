package com.app.ussd.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.app.Country;
import com.app.ussd.model.app.ServiceCode;


public interface ServiceCodeRepository extends JpaRepository<ServiceCode, Long> {

	public Optional<ServiceCode> findByServiceCode(long sc);
    /*
		
	@Query(nativeQuery = true, value = "SELECT * FROM service_code s JOIN Country c on s.country_id = c.id   WHERE s.service_code = :sc AND c.country_code = :countryCode ")
	 Optional<ServiceCode> findServiceCodeForCountry(long sc , String countryCode); 
	 
	 */

	  Optional<ServiceCode> findByServiceCodeAndCountry(long sc , Country country);

	  Optional<ServiceCode> findByServiceCodeAndCountryAndIdNot(long sc , Country country , long id);
	
}




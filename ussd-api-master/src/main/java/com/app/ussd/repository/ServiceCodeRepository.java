package com.app.ussd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.ussd.model.ServiceCode;



public interface ServiceCodeRepository extends JpaRepository<ServiceCode, Long> {

	public Optional<ServiceCode> findByServiceCode(long sc);
	
	@Query(nativeQuery = true, value = "SELECT * FROM service_code s JOIN Country c on s.country_id = c.id   WHERE s.service_code = :sc AND c.country_code = :countryCode ")
	public Optional<ServiceCode> findServiceCodeForCountry(long sc , String countryCode);
}




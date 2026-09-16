package com.app.ussd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.Country;



public interface CountryRepository extends JpaRepository<Country, Long>{
	
	public Optional<Country> findByCountryCode(String countryCode);

}

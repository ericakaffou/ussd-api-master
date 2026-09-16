package com.app.ussd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.Covrage;


public interface CovrageRepository extends JpaRepository<Covrage, Long> {

	
	public List<Covrage> findByServiceCodeId(Long id);
	
	public Optional<Covrage> findByServiceCodeIdAndChannel(Long id , String channel);
	
	
}

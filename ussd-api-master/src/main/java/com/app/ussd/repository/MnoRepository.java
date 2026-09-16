package com.app.ussd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.Mno;

public interface MnoRepository  extends JpaRepository<Mno, Long> {
	
	public Optional <Mno> findByMccAndMncAndMnoName(String mcc , String mnc , String mnoName) ;

}

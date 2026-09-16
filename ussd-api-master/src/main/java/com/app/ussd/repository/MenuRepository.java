package com.app.ussd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.ussd.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{
	@Query("SELECT m FROM Menu m WHERE m.service = :serviceId  AND m.mainMenu = True ")
	public Menu getMainMenu(@Param("serviceId") long serviceId);
	
	public List<Menu> findByServiceId(long serviceId);
	
	

}

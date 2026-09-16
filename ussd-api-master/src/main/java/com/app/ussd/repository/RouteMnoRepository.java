package com.app.ussd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.ussd.model.RouteMno;


public interface RouteMnoRepository extends JpaRepository<RouteMno, Long>{
	
	public List<RouteMno> findByCovrageId(Long id);
	
	public List<RouteMno> findByMnoId(Long id);
	
	//Pour L'enregistrement / creation des routeMno
	public Optional<RouteMno> findByCovrageIdAndServiceIdAndMnoId(Long covrageId , Long serviceId , Long mnoId);
	
	//for Service Routing
	@Query(nativeQuery = true, value = "SELECT * FROM route_mno r JOIN mno m on r.mno_id = m.id   WHERE r.covrage_id = :covrageId AND m.mno_mame = :mnoName ")
	public  Optional<RouteMno> getScMnoRoute(Long covrageId , String mnoName);
	
	
	

}

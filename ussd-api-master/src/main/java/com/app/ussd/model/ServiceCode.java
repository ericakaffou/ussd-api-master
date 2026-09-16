package com.app.ussd.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCode extends AbstractEntity{	
		
	private long serviceCode;	
	
	private boolean dedicated ;	// ture : dedicated ; false : shared
		
	// Relation with CovrageDto
	@OneToMany(mappedBy = "serviceCode") 
	private List<Covrage> covrage;  
	
	
	@ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
	
	
	
}

/*

*/

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
	
	/*
    Cette relation car on peut avoir le meme sc dans plusieurs pays.
	Suppresion de la relation entre Counrty et Mno car transitivité !!! 
	En Outre cette dernière n'est pas utile car mno ne sert qu'à avoir le mnoName pour le routage
    */
	@ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
	
	
	
}

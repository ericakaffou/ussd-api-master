package com.app.ussd.model.app;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Mno extends AbstractEntity {
	
	
	private String mnoName;
	
	// Relation with Country 
	@ManyToOne
    @JoinColumn(name = "country_id")
    private Country country; 
		

}

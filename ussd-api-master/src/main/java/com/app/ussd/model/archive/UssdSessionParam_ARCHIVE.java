package com.app.ussd.model.archive;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.ussd.model.AbstractEntity;
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

/*
 * Cette Classe sert à recuperer la donnée saisie dans un menu USSD...
 * pour des menus de type "data".Son parametre "paramName" doit matcher avec celui dans la classe Menu.
 * Son utilisation est visible dans les services Backend
 * 
 */
public class UssdSessionParam_ARCHIVE extends AbstractEntity{

	
	private String paramName;
	
	private String paramValue;
	
	@ManyToOne
    @JoinColumn(name = "ussdSession_id")
    private UssdSession_ARCHIVE ussdSession;
	
	
	
	
	

}

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

public class UssdService extends AbstractEntity {
	
	
	
	private String serviceName;	
	
    // Relation  Service to account 	
	@ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
	
	/* 
	 Parametre utiliser pour transferer ou non les requetes au système du client.
	 L'Url à laquelle les requètes sont transférés est configurée dans RouteMno
	 car chaque MNO gère differemment les datas retournées donc un traitement 
	 different via une url differente    */
	
	private boolean forwarded;
	
	private boolean enabled = false;
	
	
	//Relation with Menu 
    @OneToMany(mappedBy = "service") 
	private List<Menu> listMenu;    
    
  
    @OneToMany(mappedBy = "service") 
  	private List<UssdSession> ussdSession;
    
    // Relation with RouteMNO 
    @OneToMany(mappedBy = "service") 
	private List<RouteMno> routeMno; 
    
 
	
	
	
	

}

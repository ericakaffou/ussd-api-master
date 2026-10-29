package com.app.ussd.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


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
	*/	
	private boolean forwarded;
	
	private boolean enabled ;	
	
	//Relation with Menu 
    @OneToMany(mappedBy = "service") 
	private List<Menu> listMenu;    
  
	//Relation with UssdSession 
    @OneToMany(mappedBy = "service") 
  	private List<UssdSession> ussdSession;


	//Relation with Covrage 
    @OneToMany(mappedBy = "service") 
	private List<Covrage> listCovrage;    


    
    
 
	
	
	
	

}

package com.app.ussd.model;

import java.util.List;
import javax.persistence.Entity;
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
public class Account extends AbstractEntity {
	
	
	  private String accountName;
	  private String email;
	  private String password;
	  private boolean enabled = false;
	  
	   //Relation with Users
	   @OneToMany(mappedBy = "account") 
	   private List<Utilisateur> listUsers;	  
	  
	 
	  //Relation with Service 
	   @OneToMany(mappedBy = "account") 
	   private List<UssdService> listServices;
    
	  
	

	
	  

}

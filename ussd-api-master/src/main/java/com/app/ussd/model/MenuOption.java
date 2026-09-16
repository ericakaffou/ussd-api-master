package com.app.ussd.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



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
public class MenuOption  extends AbstractEntity{
	
	
	private boolean isNextOption; // if yes menu is on 2 parts 	
    private String label;		
	private Integer postionNumber;		 
    private Long nextMenuId;  // Id of next menu
    
    //Relation with Menu 
    @ManyToOne
    @JoinColumn(name = "menu_level")
    private Menu menu;
    
 
	
   
    
 
	
}

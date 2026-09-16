package com.app.ussd.model;

import java.util.List;
import javax.persistence.Column;
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
public class Menu extends AbstractEntity{
	
	
    private String text;       // Titre du Menu - contenu ????	
	
    private Long nextMenuId;	/* For validation Menu oo indicate response menu.
                                Cet Id est celui d'un menu de reponse.
                                validation??????
    							*/
	
	private String paramName;   // si menu de type 1 , nom du input param
	
	@Column(columnDefinition = "boolean default false")	
	private boolean mainMenu;   // 1 if Main Menu , 0 default
	
	private int menuType;  // 0: option menu ; 1: data Input Menu ; 2:Response Menu
	
    private String serviceUrl;  // Url of restAPI service  ????
    /* Cette URL est pour un service tiers interne */
    
	@Column(columnDefinition = "boolean default true")	
	private boolean answerRequired;
	
	// Relation Menu to Service	
	
	@ManyToOne
    @JoinColumn(name = "service_id")
    private UssdService service ;
	
	// Relation with MenuOption 
	@OneToMany(mappedBy = "menu") 
	private List<MenuOption> listMenuOptions;
    
   
	
	
	
}

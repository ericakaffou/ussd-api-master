package com.app.ussd.model.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Objet utilsé pour transferer les données entre  App3 et le App2 l'applicatiom metier */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class App2Data {
	
	
	private UssdSession session ;	
	private String[] parts ;	
	private String input ;	
	private boolean newSession ;
	
}

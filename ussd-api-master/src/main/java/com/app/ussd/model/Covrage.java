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
/*
 * Classe d'integration des Shortcodes .
 *  Si le SC est dedicated , il y aura un seul covrage pour ce SC
 */
@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Covrage extends AbstractEntity  {
	
	
	@ManyToOne
    @JoinColumn(name = "service_code_id")
	private ServiceCode serviceCode;	
	
	//utiiser pour partager le SC entre plusieurs User
	private String  channel;	
	
	// Relation with RouteMNO 
	@OneToMany(mappedBy = "covrage") 
	private List<RouteMno> routeMno;
	
	
	
	
	
	
}

/*
 * Cette Classe devrait s'appeler RouteMnoClient et est utilisé pour transferer les requetes
 * provenant de l'operateur mobile vers le client third party
 * un client( service) aura une ou plusieurs route( une route par operateur mobile)
 * 
 * 
 */

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
public class RouteMno extends AbstractEntity {
	
	/* Un RouteMno appartient à un seul service qui lui appartient à un seul Account*/
	
	
	@ManyToOne
    @JoinColumn(name = "covrage_id")
    private Covrage covrage;    
    private String callBackUrl;	///  Url to forward request to client (third party) 
    /*
     * il est préferable de garder cette propriété "callBackUrl" ici plutot que dans Service car chaque operateur a son modèle de donnée;
     * en cas de service transferé au clients , on pourra avoir une url differrente coté client pour chaque opérateur
     * Ainsi le client pourra fait à son niveau le tri/ traitement par operateur
     * 
     */
    @ManyToOne
    @JoinColumn(name = "service_id")
	private UssdService  service; 
    
    @ManyToOne
    @JoinColumn(name = "mno_id")
	private Mno mno;   // avant mnoName  
    
 
    
}

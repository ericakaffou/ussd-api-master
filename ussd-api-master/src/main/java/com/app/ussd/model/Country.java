package com.app.ussd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country  {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	  private Long id;	
	
	  private String countryCode;
	  
	  private String countryName;
	  
	  //Relation with ServiceCode
	   @OneToMany(mappedBy = "country") 
	   private List<ServiceCode> listServiceCodes;	  
	  
	 
	  //Relation with Mno 
	   @OneToMany(mappedBy = "country") 
	   private List<Mno> listMnos;

}

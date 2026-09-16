package com.app.ussd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.app.ussd.dto.MnoDto;

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
public class Mno extends AbstractEntity {
	
	private String mcc;
	private String mnc;
	private String mnoName;
	
	@ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
	

	
	// Relation with RouteMno 
		 @OneToMany(mappedBy = "mno") 
		 private List<RouteMno> routeMno;  
		 
		 
		// Mapping de Account -> AccountDto
		    public  static MnoDto toDto(Mno mno) {
		    	
		    	if (mno == null) {    		
		    		return null ;
		    		// TODO throw an execption
		    	}
		    	
		    	return MnoDto.builder()
		    			.id(mno.getId())
		    			.mcc(mno.getMcc())
		    			.mnc(mno.getMnc())
		    			.mnoName(mno.getMnoName())
		    			.build() ;
		    }

}

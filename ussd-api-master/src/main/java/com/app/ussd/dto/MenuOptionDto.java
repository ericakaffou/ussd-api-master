package com.app.ussd.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuOptionDto {

	private Long id;
	@Builder.Default
	private boolean isNextOption = false ; // if yes menu is on 2 parts 	
    private String label;		
	private Integer postionNumber;	
    private Long nextMenuId;  // Id of next menu    
    private Long menuId;
    
    
   
}

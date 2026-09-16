package com.app.ussd.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RouteMnoDto {

	
	private Long id;
    private Long covrageId;    
    private String callBackUrl;		
	private Long  serviceId;  
	private Long mnoId;
	
	
	
    
}

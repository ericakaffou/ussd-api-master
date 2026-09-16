package com.app.ussd.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UssdServiceDto {
	

	private Long id;
	private String serviceName;	    
    private Long accountId;	
	private boolean forwarded;	
	private boolean enabled;
	
	
  

}

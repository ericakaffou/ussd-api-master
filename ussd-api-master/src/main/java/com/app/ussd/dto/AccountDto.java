package com.app.ussd.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
	
	
	   private Long id;
	  
	   private String accountName;
	   private String email;
	   private String password;
	  
	   private boolean enabled;	  
	   	  
	   
	  

}

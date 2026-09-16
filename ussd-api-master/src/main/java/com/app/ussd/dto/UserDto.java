package com.app.ussd.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	
	private Long id;
	//@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String username;
	//@JsonIgnore
	@NotBlank
	private String password;    
    private boolean enabled;     
    private Long accountId; 
   	private List<String> roles ;

}

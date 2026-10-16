package com.app.ussd.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MnoDto {
	
	private Long id;
	private String mcc;
	private String mnc;
	private String mnoName;
	private String[] params;  //
	
	
}

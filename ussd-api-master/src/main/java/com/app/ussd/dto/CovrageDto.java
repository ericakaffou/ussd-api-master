package com.app.ussd.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CovrageDto {
	
	private Long id;
	private Long serviceCodeId;		
	private String  channel;	
	
	
}

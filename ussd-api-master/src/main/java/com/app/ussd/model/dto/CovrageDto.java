package com.app.ussd.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CovrageDto {
	
	private Long id;
	private Long serviceCodeId;	
	private String covrageName;	
	private String  channel;	
	
	
}

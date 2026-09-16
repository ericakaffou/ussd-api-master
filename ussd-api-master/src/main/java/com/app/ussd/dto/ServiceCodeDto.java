package com.app.ussd.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceCodeDto {
	
	private Long id;
	
	private Long serviceCode;	
	
	private boolean dedicated ;	// ture : dedicated ; false : shared
	
	private String contry_code ;
	
	@JsonIgnore
	private List<CovrageDto> covrageDto;  
	
	


}

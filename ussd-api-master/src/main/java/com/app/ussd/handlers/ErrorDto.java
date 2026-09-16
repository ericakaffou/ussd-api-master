package com.app.ussd.handlers;

import java.util.ArrayList;
import java.util.List;

import com.app.ussd.exception.ErrorCodes;

import lombok.Builder;
import lombok.Data;

//Erreur renvoyer à l'utilsateur


@Data
@Builder
public class ErrorDto {
	
	private Integer httpCode;
	
	private ErrorCodes code;
	
	private String message;
	@Builder.Default
	private List<String> erros = new ArrayList<String>();
	
	

}

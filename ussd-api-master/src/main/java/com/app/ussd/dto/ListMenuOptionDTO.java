package com.app.ussd.dto;

import java.util.List;

import lombok.Data;
// Classe créé pour permettre d'enregistrer plusieur option d'un menu d'un seul coup mais pas use encore
@Data
public class ListMenuOptionDTO {
	
	private long menuLevel;
	
	private List<MenuOptionDto> option;

}

package com.app.ussd.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ussd.dto.UssdSessionDto;
import com.app.ussd.service.UssdSessionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@Api("${app.root.url}" +"/ussdSessions")
@RequestMapping("${app.root.url}")
public class UssdSessionController {
	
	 private final  UssdSessionService sessionService;	
	
	
			public UssdSessionController(UssdSessionService sessionService) {
		super();
		this.sessionService = sessionService;
	}

			//ListAll
			@GetMapping(value = "/ussdSessions/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
			@ApiOperation(value = "Renvoi toutes les  Sessions USSD" , notes = "cette methode permet de renvoyer toutes les  Sessions USSD enregistrées" , responseContainer = "UssdSessionDto") 
					@ApiResponses(value = {
						@ApiResponse(code = 200 , message = "La liste des Sessions USSD / une liste vide")
					
			})	public List<UssdSessionDto> ListAll() {
					 
				return sessionService.FindAll();
			}
			
			//FindUssdSessionByServiceId
			@GetMapping(value = "/ussdSessions/service{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
			@ApiOperation(value = "Rechercher la liste des sessions d'un service " , notes = "cette methode permet de chercher la liste des sessions d'un service" , response = UssdSessionDto.class) 
			@ApiResponses(value = {
					 @ApiResponse(code = 200 , message = "la liste des sessions du service trouvée"),
					 @ApiResponse(code = 404 , message = "Aucune liste trouvée ")
			})	public List<UssdSessionDto> FindByServiceId(Long id) {
				
				return sessionService.FindUssdSessionsByServiceId(id);
			}

}

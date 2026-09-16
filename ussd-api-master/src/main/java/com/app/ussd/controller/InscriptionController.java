package com.app.ussd.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.ussd.dto.AccountDto;
import com.app.ussd.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//OK---check
@CrossOrigin("*")
@Api("/inscriptionController")
@RestController

public class InscriptionController  {
	
	
   	private final  AccountService accountService;

   	
	public InscriptionController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}


	//Save &Edit
	@PostMapping(value = "/inscriptionController/" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Enregistrer Ou Modifier un Account" , notes = "cette methode permet d'enregistrer  Ou Modifier  un Account" , response = AccountDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Account Cree/Modifié "),
			 @ApiResponse(code = 400 , message = "l'objet Account n'est pas valide")
	})	public AccountDto Save(@RequestBody AccountDto dto) {
		
		return accountService.Save(dto);
	}

}

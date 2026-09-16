package com.app.ussd.controller;

import java.util.List;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.ussd.dto.AccountDto;
import com.app.ussd.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@Api("${app.root.url}" +"/account")
@RestController
@RequestMapping("${app.root.url}")
public class AccountController  {
	
	
   	private final AccountService accountService;
	
	

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	//Save &Edit
	@PostMapping(value = "/account/" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Enregistrer Ou Modifier un Account" , notes = "cette methode permet d'enregistrer  Ou Modifier  un Account" , response = AccountDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Account Cree/Modifié "),
			 @ApiResponse(code = 400 , message = "l'objet Account n'est pas valide")
	})	public AccountDto Save(@RequestBody AccountDto dto) {
		
		return accountService.Save(dto);
	}

	//FindById
	@GetMapping(value = "/account/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher un Account" , notes = "cette methode permet de chercher un Account par son id" , response = AccountDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Account trouvé dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucun Account trouvé dans la BDD")
	})	public AccountDto FindById(@PathVariable("id") Long id) {

		return accountService.FindById(id);
	}

	//ListAll
	@GetMapping(value = "/accounts/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Renvoi tous les  accounts" , notes = "cette methode permet de renvoyer la liste des accounts dans la BDD" , responseContainer = "AccountDto") 
			@ApiResponses(value = {
				@ApiResponse(code = 200 , message = "La liste des accounts / une liste vide")
			
	})	public List<AccountDto> ListAll() {
			 
		return accountService.FindAll();
	}

	
	//Delete
	@DeleteMapping(value = "/account/delete/{id}")
	@ApiOperation(value = "Supprimer un account" , notes = "cette methode permet de supprimer un account par son ID" ) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Account a ete supprimé dans la BDD")
	})	public void Delete(@PathVariable("id") Long id) {
		
		accountService.Delete(id);
		
	}

	

	//FindByAccount
	@GetMapping(value ="/account/name/{name}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher un Account" , notes = "cette methode permet de chercher un Account par son nom" , response = AccountDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Account trouvé dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucun Account trouvé dans la BDD")
	})	public AccountDto FindByAccountName(@PathVariable("name") String name) {
		
		return accountService.FindByAccountName(name);
	}

	

	

}

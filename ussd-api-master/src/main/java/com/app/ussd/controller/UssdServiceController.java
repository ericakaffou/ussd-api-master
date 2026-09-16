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

import com.app.ussd.dto.UssdServiceDto;
import com.app.ussd.service.UssdServiceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@Api("${app.root.url}" +"/service")
@RequestMapping("${app.root.url}")
public class UssdServiceController {
	
	
   	private final UssdServiceService ussdService;
	
	
	
	public UssdServiceController(UssdServiceService ussdService) {
		super();
		this.ussdService = ussdService;
	}

	@PostMapping(value ="/service/" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Enregistrer Ou Modifier un service" , notes = "cette methode permet d'Enregistrer Ou Modifier  un service" , response = UssdServiceDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet service cree / modifié "),
			 @ApiResponse(code = 400 , message = "l'objet service n'est pas valide")
	})	public UssdServiceDto Save(@RequestBody UssdServiceDto dto) throws Exception {
		return ussdService.Save(dto);

	}
	
	//FindById
	@GetMapping(value ="/service/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher un service" , notes = "cette methode permet de chercher un service par son id" , response = UssdServiceDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet service trouvé dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucun service trouvé dans la BDD")
	})	public UssdServiceDto FindById(@PathVariable("id") Long id) {
		return ussdService.FindById(id);
	}
	
		//FindAll
		@GetMapping(value ="/services/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Renvoi tous les  services" , notes = "cette methode permet de renvoyer la liste des services dans la BDD" , responseContainer = "UssdServiceDto") 
		@ApiResponses(value = {
		@ApiResponse(code = 200 , message = "La liste des services / une liste vide")			
		})	public List<UssdServiceDto> FindAll() {
			return ussdService.FindAll();
			}

		//Delete
		@DeleteMapping(value ="/service/delete/{id}")
		@ApiOperation(value = "Supprimer un service" , notes = "cette methode permet de supprimer un service par son ID" ) 
		@ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "l'objet service a ete supprimé dans la BDD")
		})	public void delete(@PathVariable("id") Long id) throws Exception {
			ussdService.Delete(id);
		}
		
		//FindByAccountId
		@GetMapping(value ="/services/account/{id}" ,  produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Renvoi tous les  services d'un compte client" , notes = "cette methode permet de renvoyer la liste des services d'un compte client" , responseContainer = "UssdServiceDto") 
		@ApiResponses(value = {
		@ApiResponse(code = 200 , message = "La liste des services d'un Account / une liste vide")			
		})	public List<UssdServiceDto> FindByAccountId(@PathVariable("id") Long id) {
			return ussdService.FindByAccountId(id);
			}
		
}

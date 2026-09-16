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

import com.app.ussd.dto.CovrageDto;
//import com.app.ussd.dto.ResponseDTO;
import com.app.ussd.service.CovrageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@Api("${app.root.url}" +"/covrage")
@RestController
@RequestMapping("${app.root.url}")
public class CovrageController {
	
	
   	private final CovrageService covrageService;
	
	

	public CovrageController(CovrageService covrageService) {
		super();
		this.covrageService = covrageService;
	}

	@PostMapping(value ="/covrage/" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Enregistrer ou modidier un Covrage" , notes = "cette methode permet d'enregistrer ou modidié  un Covrage" , response = CovrageDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Covrage cree/modidié "),
			 @ApiResponse(code = 400 , message = "l'objet Covrage n'est pas valide")
	})	public CovrageDto Save(@RequestBody CovrageDto dto) throws Exception {
		
		return covrageService.Save(dto);
	}

	@GetMapping(value ="/covrage/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher un Covrage" , notes = "cette methode permet de chercher un Covrage par son id" , response = CovrageDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Covrage trouvé dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucun Covrage trouvé dans la BDD")
	})	public CovrageDto FindById(@PathVariable("id") Long id) {
		return covrageService.FindById(id);
	}

	

	@GetMapping(value ="/covrage/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Renvoi tous les  Covrages" , notes = "cette methode permet de renvoyer la liste des Covrages dans la BDD" , responseContainer = "CovrageDto") 
	@ApiResponses(value = {
	@ApiResponse(code = 200 , message = "La liste des Covrages / une liste vide")			
	})	public List<CovrageDto> FindAll() {		
		return covrageService.FindAll();
		
	}
	
	@GetMapping(value ="/covrage/sc/{id}" ,  produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Renvoi tous les  Covrages d'un Service Code" , notes = "cette methode permet de renvoyer la liste des Covrages d'un Service Code dans la BDD" , responseContainer = "CovrageDto") 
	@ApiResponses(value = {
			@ApiResponse(code = 200 , message = "La liste des Covrages d'un Service Code / une liste vide")			
	})	public List<CovrageDto> FindByServiceCodeId(@PathVariable("id") Long id) {
		
		return covrageService.FindByServiceCodeId(id);
	}

	@DeleteMapping(value ="/covrage/delete/{id}")
	@ApiOperation(value = "Supprimer un Covrage" , notes = "cette methode permet de supprimer un Covrage par son ID" ) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Covrage a ete supprimé dans la BDD")
	})	public void Delete(@PathVariable("id") Long id) {
		  covrageService.Delete(id);
		
	}

}

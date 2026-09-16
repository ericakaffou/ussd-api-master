package com.app.ussd.controller;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ussd.dto.RouteMnoDto;
import com.app.ussd.service.RouteMnoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("${app.root.url}" +"/RouteMno")
@RequestMapping("${app.root.url}")
public class RouteMnoController {
	
	
   	private final  RouteMnoService routeMnoService;
	
	
	public RouteMnoController(RouteMnoService routeMnoService) {
		super();
		this.routeMnoService = routeMnoService;
	}

	//Save
	@PostMapping(value ="/routemno/" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Enregistrer ou Modifier une RouteMno" , notes = "cette methode permet d'enregistrer  ou Modifier une RouteMno" , response = RouteMnoDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet RouteMno cree/modifié "),
			 @ApiResponse(code = 400 , message = "l'objet RouteMno n'est pas valide")
	})	public RouteMnoDto Save(@RequestBody RouteMnoDto dto) {
		return routeMnoService.Save(dto);
	}

	//FindById
	@GetMapping(value ="/routemno/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher une RouteMno" , notes = "cette methode permet de chercher une RouteMno par son id" , response = RouteMnoDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet RouteMno trouvé dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucun RouteMno trouvé dans la BDD")
	})	public RouteMnoDto FindById(@PathVariable("id") Long id) {
		return routeMnoService.FindById(id);
	}

	//FindByCovrageId
	@GetMapping(value ="/routemno/covrage/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher un RouteMno" , notes = "cette methode permet de lister toutes les RouteMno du meme covrage" , response = RouteMnoDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet RouteMno trouvé dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucun RouteMno trouvé dans la BDD")
	})	public List<RouteMnoDto> FindByCoverageId(@PathVariable("id") Long id) {
		return routeMnoService.FindByCoverageId(id);
	}

	//FindAll
	@GetMapping(value ="/routemno/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Renvoi toutes les  RouteMnos" , notes = "cette methode permet de renvoyer la liste des RouteMnos dans la BDD" , responseContainer = "RouteMnoDto") 
	@ApiResponses(value = {
	@ApiResponse(code = 200 , message = "La liste des RouteMnos / une liste vide")			
	})	public List<RouteMnoDto> FindAll() {
		return routeMnoService.findAll();
		}

	//Delete
	@DeleteMapping(value ="/routemno/delete/{id}")
	@ApiOperation(value = "Supprimer un RouteMno" , notes = "cette methode permet de supprimer un RouteMno par son ID" ) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet RouteMno a ete supprimé dans la BDD")
	})	public void delete(@PathVariable("id") Long id) {
		 routeMnoService.Delete(id);
	}

}

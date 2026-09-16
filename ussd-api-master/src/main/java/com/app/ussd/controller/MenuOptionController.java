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

import com.app.ussd.dto.MenuOptionDto;
import com.app.ussd.service.MenuOptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@Api("${app.root.url}" +"/menuOption")
@RestController
@RequestMapping("${app.root.url}")
public class MenuOptionController {
	
	
	 private  final MenuOptionService menuOptionService;
	
	
	public MenuOptionController(MenuOptionService menuOptionService) {
		super();
		this.menuOptionService = menuOptionService;
	}


	//Save
	@PostMapping(value ="/menuoption" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Enregistrer ou modifier une option de Menu" , notes = "cette methode permet d'enregistrer  ou modifier une option de Menut" , response = MenuOptionDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet menuOption cree /Modifié "),
			 @ApiResponse(code = 400 , message = "l'objet menuOption n'est pas valide")
	})	public MenuOptionDto Save(@RequestBody  MenuOptionDto dto) throws Exception {
		
		return menuOptionService.Save(dto);
	}
	
	
	//listAll
	@GetMapping(value ="/menuoptions/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Renvoi toutes les  options dans la BD" , notes = "cette methode permet de renvoyer toutes les  options dans la BD" , responseContainer = "MenuOptionDto") 
			@ApiResponses(value = {
				@ApiResponse(code = 200 , message = "La liste des options dans la BD / une liste vide")
			
	})		 public List<MenuOptionDto> ListAll() {
			 
			 return menuOptionService.FindAll();
			 
		 }
	
	//find by id 
	@GetMapping(value ="/menuoption/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher une  option" , notes = "cette methode permet de chercher une  option par son id" , response = MenuOptionDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet option trouvée dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucune option trouvée dans la BDD")
	})
		public MenuOptionDto FindbyId(@PathVariable("id") Long id) {
			return menuOptionService.FindbyId(id);
			
		}
	
	// ListMenuOption BY MenuId 
	@GetMapping(value ="/menuoption/menu/{id}" ,  produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Renvoi toutes les  options d'un menu" , notes = "cette methode permet de renvoyer toutes les  options d'un menu" , responseContainer = "MenuOptionDto") 
	@ApiResponses(value = {
			@ApiResponse(code = 200 , message = "La liste des  options d'un menu / une liste vide")			
	})	
			 public List<MenuOptionDto> ListMenuOptionsByMenuId(@PathVariable("id") Long id) {
				 return menuOptionService.ListMenuOptionsByMenuId(id);
			 }
	 

	
	//Delete
	@DeleteMapping(value ="/menuoption/delete/{id}")
	@ApiOperation(value = "Supprimer une option" , notes = "cette methode permet de supprimer une option par son ID" ) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l' option a ete supprimeé ")
	})	
	 public void Delete(@PathVariable("id") Long id)  {
		menuOptionService.Delete(id);
	}

}

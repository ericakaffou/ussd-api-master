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

import com.app.ussd.dto.MenuDto;
import com.app.ussd.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@Api("${app.root.url}" +"/menu")
@RestController
@RequestMapping("${app.root.url}")
public class MenuController {

	
   	private final  MenuService menuService;
	
	   public MenuController(MenuService menuService) {
		super();
		this.menuService = menuService;
	}

	//Save
	@PostMapping(value ="/menu" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Enregistrer ou modifier un menu" , notes = "cette methode permet d'enregistrer ou modifier un Menu" , response = MenuDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet Menu cree/modifié "),
			 @ApiResponse(code = 400 , message = "l'objet Menu n'est pas valide")
	})public MenuDto Save(@RequestBody MenuDto dto) throws Exception {
			
			return menuService.Save(dto);
		}

	//findById 
	@GetMapping(value ="/menu/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher un menu" , notes = "cette methode permet de chercher un menu par son id" , response = MenuDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet menu trouvé dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucun menu trouvé dans la BDD")
	})	public MenuDto FindById(@PathVariable("id") Long id){
			return menuService.FindById(id);
		}
	
	
	//ListAll
		@GetMapping(value ="/menus/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Renvoi tous les  menus" , notes = "cette methode permet de renvoyer la liste des menus dans la BDD" , responseContainer = "MenuDto") 
				@ApiResponses(value = {
					@ApiResponse(code = 200 , message = "La liste des menus / une liste vide")
				
		})	public List<MenuDto> ListAll()  {	
			return menuService.FindAll();
		}
		
		//Delete
		@DeleteMapping(value ="/menu/delete/{id}")
		@ApiOperation(value = "Supprimer un menu" , notes = "cette methode permet de supprimer un menu par son ID" ) 
		@ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "l'objet menu a ete supprimé dans la BDD")
		})	public void Delete(@PathVariable("id") Long id) throws Exception {
			menuService.Delete(id);
		}
}

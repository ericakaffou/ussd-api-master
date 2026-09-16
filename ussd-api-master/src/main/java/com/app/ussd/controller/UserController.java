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

import com.app.ussd.dto.UserDto;
import com.app.ussd.service.UtilisateurService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@Api("${app.root.url}" +"/user")
@RequestMapping("${app.root.url}")
public class UserController {
	
	
   	private final  UtilisateurService utilisateurService;
	
	
	public UserController(UtilisateurService utilisateurService) {
		super();
		this.utilisateurService = utilisateurService;
	}

	//Save
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value ="/user/" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Enregistrer  Ou Modifier un user" , notes = "cette methode permet d'enregistrer Ou Modifier  un user" , response = UserDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet user cree/Modifié "),
			 @ApiResponse(code = 400 , message = "l'objet user n'est pas valide")
	})	public UserDto Save(@RequestBody  UserDto dto) throws Exception  {
		return utilisateurService.Save(dto);

	}
	
	//FindById
	@GetMapping(value = "/user/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Rechercher un user" , notes = "cette methode permet de chercher un user par son id" , response = UserDto.class) 
	@ApiResponses(value = {
			 @ApiResponse(code = 200 , message = "l'objet user trouvé dans la BDD"),
			 @ApiResponse(code = 404 , message = "Aucun user trouvé dans la BDD")
	})	public UserDto FindById(@PathVariable("id") Long id) {
		return utilisateurService.FindById(id);
	}
	
	

		//FindAll
		@GetMapping(value ="/users/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Renvoi tous les  users" , notes = "cette methode permet de renvoyer la liste des users dans la BDD" , responseContainer = "UserDto") 
		@ApiResponses(value = {
		@ApiResponse(code = 200 , message = "La liste des User/ une liste vide")			
		})	public List<UserDto> FindAll() {
			return utilisateurService.FindAll();
			}

		//Delete
		@DeleteMapping(value ="/user/delete/{id}")
		@ApiOperation(value = "Supprimer un user" , notes = "cette methode permet de supprimer un user par son ID" ) 
		@ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "l'objet user a ete supprimé dans la BDD")
		})	public void delete(@PathVariable("id") Long id) throws Exception {
			utilisateurService.Delete(id);
		}
		
		//FindByAccountId
		@GetMapping(value ="/user/account/{id}" ,  produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Renvoi tous les  users d'un compte client" , notes = "cette methode permet de renvoyer la liste des users d'un compte client" , responseContainer = "UserDto") 
		@ApiResponses(value = {
		@ApiResponse(code = 200 , message = "La liste des users  d'un compte/ une liste vide")			
		})	public List<UserDto> FindByAccountId(@PathVariable("id") Long id) {
			return utilisateurService.FindByAccountId(id);
			}
		

}

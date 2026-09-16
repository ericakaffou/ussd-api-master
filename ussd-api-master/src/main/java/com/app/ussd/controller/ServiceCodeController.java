package com.app.ussd.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ussd.dto.ServiceCodeDto;
import com.app.ussd.service.ServiceCodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



//OK---check
@CrossOrigin("*")
@Api("${app.root.url}" +"/scode")
@RestController
@RequestMapping("${app.root.url}")
public class ServiceCodeController {
	
	   private final ServiceCodeService scService;	
	  
	  
	  public ServiceCodeController(ServiceCodeService scService) {
		super();
		this.scService = scService;
	}

	  //Save
	  @PreAuthorize("hasAuthority('ADMIN')")
	  @PostMapping(value ="/scode/" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	  @ApiOperation(value = "Enregistrer Ou Modifier un Service Code" , notes = "cette methode permet d'enregistrer Ou Modifier un Service Code" , response = ServiceCodeDto.class) 
	  @ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "l'objet ServiceCode cree/Modifié "),
				 @ApiResponse(code = 400 , message = "l'objet ServiceCode n'est pas valide")
		})
		public ServiceCodeDto Save(@RequestBody ServiceCodeDto dto) {
			return scService.Save(dto);
		}
	  
	  	//FindById
		@GetMapping(value ="/scode/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Rechercher un Service Code" , notes = "cette methode permet de chercher un Service Code par son id" , response = ServiceCodeDto.class) 
		@ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "l'objet ServiceCode trouvé dans la BDD"),
				 @ApiResponse(code = 404 , message = "Aucun ServiceCode trouvé dans la BDD")
		})	public ServiceCodeDto FindById(@PathVariable("id") Long id) {

			return scService.FindById(id);
		}
		
		//ListAll
		@GetMapping(value ="/scodes/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Renvoi tous les  Service Code" , notes = "cette methode permet de renvoyer la liste des Service Code" , responseContainer = "ServiceCodeDto") 
				@ApiResponses(value = {
					@ApiResponse(code = 200 , message = "La liste des Service Code / une liste vide")
				
		})	public List<ServiceCodeDto> ListAll() {
				 
			return scService.FindAll();
		}
		
		//Delete
		@PreAuthorize("hasAuthority('ADMIN')")
		@DeleteMapping(value ="/scode/delete/{id}")
		@ApiOperation(value = "Supprimer un Service Code" , notes = "cette methode permet de supprimer un Service Code par son ID" ) 
		@ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "le Service Code a ete supprimé dans la BDD")
		})	public void Delete(@PathVariable("id") Long id) {
			
			scService.Delete(id);
			
		}
		
		

}

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

import com.app.ussd.dto.MnoDto;
import com.app.ussd.service.MnoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@Api("${app.root.url}" +"/mno")
@RequestMapping("${app.root.url}")
public class MnoController {
	
   private  final MnoService mnoService;	
	  
	  
	  public MnoController(MnoService mnoService) {
	super();
	this.mnoService = mnoService;
}

	  //Save
	  @PostMapping(value ="/mno/" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Enregistrer Ou Modifier un Operateur mobil" , notes = "cette methode permet d'enregistrer Ou Modifier un Operateur mobil" , response = MnoDto.class) 
		@ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "l' Operateur mobil cree/Modifié "),
				 @ApiResponse(code = 400 , message = "l'Operateur mobil n'est pas valide")
		})
		public MnoDto Save(@RequestBody MnoDto dto) {
			return mnoService.Save(dto);
		}
	  
	  	//FindById
		@GetMapping(value ="/mno/{id}" , produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Rechercher un Operateur mobil" , notes = "cette methode permet de chercher un Operateur mobil par son id" , response = MnoDto.class) 
		@ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "l'objet Operateur mobil trouvé dans la BDD"),
				 @ApiResponse(code = 404 , message = "Aucun Operateur mobil trouvé dans la BDD")
		})	public MnoDto FindById(@PathVariable("id") Long id) {

			return mnoService.FindById(id);
		}
		
		//ListAll
		@GetMapping(value ="/mnos/" ,  produces = MediaType.APPLICATION_JSON_VALUE )
		@ApiOperation(value = "Renvoi tous les  Operateurs mobils" , notes = "cette methode permet de renvoyer la liste des Operateurs mobils enregistrés" , responseContainer = "MnoDto") 
				@ApiResponses(value = {
					@ApiResponse(code = 200 , message = "La liste des Operateurs mobiles  / une liste vide")
				
		})	public List<MnoDto> ListAll() {
				 
			return mnoService.FindAll();
		}
		
		//Delete
		@DeleteMapping(value ="/mno/delete/{id}")
		@ApiOperation(value = "Supprimer un Operateur mobil" , notes = "cette methode permet de supprimer un Operateur mobil par son ID" ) 
		@ApiResponses(value = {
				 @ApiResponse(code = 200 , message = "l' Operateur mobil a ete supprimé dans la BDD")
		})	public void Delete(@PathVariable("id") Long id) {
			
			mnoService.Delete(id);
			
		}
		
		

}

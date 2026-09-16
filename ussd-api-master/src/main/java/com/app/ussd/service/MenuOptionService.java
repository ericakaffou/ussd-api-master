package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ussd.dto.MenuOptionDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Menu;
import com.app.ussd.model.MenuOption;
import com.app.ussd.repository.MenuOptionRepository;
import com.app.ussd.repository.MenuRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.MenuOptionValidator;

@Service
public class MenuOptionService {
	
	 private final  MenuOptionRepository menuOptionRepo;
	 private final  MenuRepository menuRepo;	
	 
	 
	
	public MenuOptionService(MenuOptionRepository menuOptionRepo, MenuRepository menuRepo) {
		super();
		this.menuOptionRepo = menuOptionRepo;
		this.menuRepo = menuRepo;
	}
	//Save ok
	public MenuOptionDto Save(MenuOptionDto dto) throws java.lang.Exception {
		
		List<String> errors = MenuOptionValidator.validate(dto);
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.MENU_OPTION_NOT_VALID, errors);
		}
		
		 //Check and get menu
		 Optional<Menu> optionalMenu = menuRepo.findById(dto.getMenuId());		 
		 if(optionalMenu.isEmpty()) {
			 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MENU_NOT_FOUND," Menu with id = "+ dto.getMenuId() +"Not Found");
		
		   }
		 
		 //Check Exist menuOption
		 Optional<MenuOption> optionalMenuOption = menuOptionRepo.findByPostionNumber(dto.getPostionNumber());
		 if(!optionalMenuOption.isEmpty()) {
			 throw new Exception (Constants.FAILED,ErrorCodes.MENU_OPTION_EXIST);
		
		   }		
		
		MenuOption option = new MenuOption();
		option.setId(dto.getId());
		option.setLabel(dto.getLabel());
		option.setMenu(optionalMenu.get());
		option.setNextMenuId(dto.getNextMenuId());
		option.setNextOption(dto.isNextOption());
		option.setPostionNumber(dto.getPostionNumber());
		
		
		return this.toDto(menuOptionRepo.save(option));
		     
	}
	// ListMenuOption BY MenuId ok
		 public List<MenuOptionDto> ListMenuOptionsByMenuId(Long id) {
			 
			 if (id == null) {
				 throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);
				   }
		
			 //Check and get menu
			 Optional<Menu> optionalMenu = menuRepo.findById(id);
			 List<MenuOptionDto> listMenuOptionDto = new ArrayList<MenuOptionDto>();
			 if(optionalMenu.isEmpty()) {
				 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MENU_NOT_FOUND," Menu with id = "+ id +"Not Found");
			
			   }
			 
			 List<MenuOption> optionalListMenuOption = menuOptionRepo.findByMenuId(optionalMenu.get().getId());
			 if ( optionalListMenuOption.isEmpty() ) {
				 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MENU_OPTION_NOT_FOUND);
				   }		 
			 for( MenuOption menuOption : optionalListMenuOption ) {
				 listMenuOptionDto.add(this.toDto(menuOption))  ;			
				}			   
			 return listMenuOptionDto;
		 }
	
	//listAll ok
	 public List<MenuOptionDto> FindAll() {
		 
		 List<MenuOptionDto> listMenuOptionDto = new ArrayList<MenuOptionDto>();
			 
		 List<MenuOption> optionalListMenuOption = menuOptionRepo.findAll();
		if (optionalListMenuOption.isEmpty()) {
			throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MENU_OPTION_NOT_FOUND);
			}
		 for( MenuOption menuOption : optionalListMenuOption ) {
			 listMenuOptionDto.add(this.toDto(menuOption))  ;			
			}
			   
		 return listMenuOptionDto;
	 }
	
	
	//findMenuoption by id OK
	public MenuOptionDto FindbyId(Long id) {
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);
		   }		
		 Optional<MenuOption> optionalMenuOption = menuOptionRepo.findById(id);
		 if(optionalMenuOption.isEmpty()) {
			 
			 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MENU_OPTION_NOT_FOUND);
		}		 
	         return this.toDto(optionalMenuOption.get());		
	}
	
	
	 
	 //delete ok
	 
	 public void Delete(Long id)  {		 
		 MenuOptionDto option = this.FindbyId(id);
		 menuOptionRepo.deleteById(option.getId());		 
		 
	 }
	 
	// Mapping de MenuOption -> MenuOptionDto
	    public   MenuOptionDto toDto(MenuOption menuOption) {
	    	
	    	if (menuOption == null) {    		
	    		return null ;
	    		// TODO throw an execption
	    	}
	    	
	    	return MenuOptionDto.builder()
	    			.id(menuOption.getId())
	    			.isNextOption(menuOption.isNextOption())
	    			.label(menuOption.getLabel())
	    			.postionNumber(menuOption.getPostionNumber())
	    			.nextMenuId(menuOption.getNextMenuId())
	    			.menuId(menuOption.getMenu().getId())
	    			.build() ;
	    }

}

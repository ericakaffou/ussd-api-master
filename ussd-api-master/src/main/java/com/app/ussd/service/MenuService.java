package com.app.ussd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ussd.dto.MenuDto;
import com.app.ussd.exception.EntityNotFoundException;
import com.app.ussd.exception.ErrorCodes;
import com.app.ussd.exception.Exception;
import com.app.ussd.exception.InvalidEntityException;
import com.app.ussd.model.Menu;
import com.app.ussd.model.MenuOption;
import com.app.ussd.model.UssdService;
import com.app.ussd.repository.MenuOptionRepository;
import com.app.ussd.repository.MenuRepository;
import com.app.ussd.repository.ServiceRepository;
import com.app.ussd.utils.Constants;
import com.app.ussd.validator.MenuValidator;



@Service
public class MenuService {
	
	 private final  MenuRepository menuRepo;
	 private final MenuOptionRepository optionRepo;	
	 private final  ServiceRepository serviceRepo;
	
	 
	
	
	public MenuService(MenuRepository menuRepo, MenuOptionRepository optionRepo, ServiceRepository serviceRepo) {
		super();
		this.menuRepo = menuRepo;
		this.optionRepo = optionRepo;
		this.serviceRepo = serviceRepo;
	}


	//save ok
	public MenuDto Save(MenuDto dto) throws java.lang.Exception {
		
		List<String> errors = MenuValidator.validate(dto);
		if(!errors.isEmpty()) {
			 throw new InvalidEntityException (Constants.ERROR,ErrorCodes.MENU_NOT_VALID, errors);
		}
		
		 Optional<UssdService> optionalService = serviceRepo.findById(dto.getServiceId());
		 if(optionalService.isEmpty()) {
			 throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.SERVICE_NOT_FOUND);
		 }
		
		Menu menu = new Menu();
		menu.setId(dto.getId());
		menu.setText(dto.getText());
		menu.setNextMenuId(dto.getNextMenuId());
		menu.setParamName(dto.getParamName());
		menu.setMainMenu(dto.isMainMenu());
		menu.setMenuType(dto.getMenuType());
		menu.setServiceUrl(dto.getServiceUrl());
		menu.setAnswerRequired(dto.isAnswerRequired());
		menu.setService(optionalService.get());
		
		return this.toDto(menuRepo.save(menu));
	}

	
	//findById ok
	public MenuDto FindById(Long id){
		if (id == null) {
			throw new Exception(Constants.ERROR,ErrorCodes.ID_NULL);
		   }
		
		Optional<Menu> optionalMenu = menuRepo.findById(id);
		 if ( optionalMenu.isEmpty() ) {
			  throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MENU_NOT_FOUND);			  
		  }
		
        return this.toDto(optionalMenu.get());
	
		
	}
	
	//FindAll ok
	public List<MenuDto> FindAll()  {	
		
		 List<MenuDto> listMenuDto = new ArrayList<MenuDto>();
		 
		 List<Menu> optionalListMenu = menuRepo.findAll();
		if (optionalListMenu.isEmpty()) {
			throw new EntityNotFoundException (Constants.FAILED,ErrorCodes.MENU_NOT_FOUND);
		}
		 for( Menu menu : optionalListMenu ) {
			 listMenuDto.add(this.toDto(menu))  ;			
			}			   
		 return listMenuDto;
		
	}	
	
	public void Delete(Long id) throws java.lang.Exception {
					
		MenuDto menu = this.FindById(id);	        
		List<MenuOption> listMenuOption = optionRepo.findByMenuId(menu.getId());
		if (!listMenuOption.isEmpty()) {
			throw new Exception(Constants.FAILED,ErrorCodes.MENU_OPTION_EXIST, "Delete All option of this Menu before");								
		}	
		menuRepo.deleteById(menu.getId());			
					
			
		} 
	
	// Mapping de Menu -> MenuDto
    public   MenuDto toDto(Menu menu) {
    	
    	if (menu == null) {    		
    		return null ;
    		// TODO throw an execption
    	}
    	
    	return MenuDto.builder()
    			.id(menu.getId())
    			.text(menu.getText())
    			.nextMenuId(menu.getNextMenuId())
    			.paramName(menu.getParamName())
    			.mainMenu(menu.isMainMenu())
    			.menuType(menu.getMenuType())
    			.serviceUrl(menu.getServiceUrl())
    			.answerRequired(menu.isAnswerRequired())
    			.serviceId(menu.getService().getId())    			
    			.build() ;
    }

}

package com.app.ussd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ussd.model.MenuOption;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {

	 public List<MenuOption> findByMenuId(Long id);
	 
	 public Optional <MenuOption> findByPostionNumber (Integer postionNumber);
	 
	 
}

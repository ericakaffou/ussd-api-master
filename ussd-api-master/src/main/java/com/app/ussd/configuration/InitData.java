package com.app.ussd.configuration;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.ussd.model.Account;
import com.app.ussd.model.Roles;
import com.app.ussd.model.Utilisateur;
import com.app.ussd.repository.AccountRepository;
import com.app.ussd.repository.UtilisateurRepository;
import com.app.ussd.service.RoleService;

@Component
public class InitData {
	
	private final AccountRepository accountRepo;	
	private final UtilisateurRepository userRepo;	
	private  final RoleService roleService;


    
   
	public InitData(AccountRepository accountRepo, UtilisateurRepository userRepo, RoleService roleService) {
		this.accountRepo = accountRepo;
		this.userRepo = userRepo;
		this.roleService = roleService;
	}




	@Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
    	
    	
    	Optional<Account> optionalAccount = accountRepo.findByAccountName("SUPADMIN");
    	if(optionalAccount.isEmpty()) {
    	
    	
        // Initialisation exécutée une seule fois au démarrage
    	Account account = new Account();
	    account.setAccountName("SUPADMIN");
	    account.setEmail("ericakaffou@gmail.com");
	    account.setEnabled(true);
	    
	    // TODO //
	    //Account accountnew = accountRepo.save(account);
	    
	      Utilisateur newUser = new Utilisateur();
		  newUser.setAccount(accountRepo.save(account));
		  newUser.setUsername("ericakaffou@gmail.com");
		  //Encodage du mot de passe 
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		  String passwordHash = encoder.encode("azerty123");
		  newUser.setPassword(passwordHash);		 
		  
		  // Add role ADMIN to NEW User  
		  Set<Roles> roles = new HashSet<>();		  
		  Roles role = roleService.findByName("SUPADMIN");
		  roles.add(role);				
		  newUser.setRoles(roles); // Bug géré avec @Transactional
		  newUser.setEnabled(true);	
		  userRepo.save(newUser);
		  
    }
}
    
} 
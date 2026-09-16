package com.app.ussd.service.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.ussd.model.Utilisateur;
import com.app.ussd.repository.UtilisateurRepository;
import com.app.ussd.utils.Constants;


@Service
public class ApplicationUserDetailsService  implements UserDetailsService {
	
    private final UtilisateurRepository userRepo;
    
    public ApplicationUserDetailsService(UtilisateurRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//Ici utilisez le repo plutot que le service car via le repo on a l'objet complé avec les Roles associés
		// sinon avec le Service le dto renvoyé ne contient pas les Roles
		Optional<Utilisateur> optionalUser = userRepo.findByUsername(email);
		if(optionalUser.isEmpty()) {
			 throw new BadCredentialsException(Constants.FAILED); 
		}	
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();	
		
		
		optionalUser.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
		
		return new User(optionalUser.get().getUsername(),optionalUser.get().getPassword(),authorities); 
	}
}


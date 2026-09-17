package com.app.ussd.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ussd.dto.auth.AuthenticationRequest;
import com.app.ussd.dto.auth.AuthenticationResponse;
import com.app.ussd.service.auth.ApplicationUserDetailsService;
import com.app.ussd.service.auth.JwtUtil;
import com.app.ussd.utils.Constants;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("${app.root.url}" + "/auth")
public class AuthenticationController {	
	  
	private final ApplicationUserDetailsService userDetailsService;

	    
	private final JwtUtil jwtUtil ;
	
	public AuthenticationController(ApplicationUserDetailsService userDetailsService, JwtUtil jwtUtil) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}


	
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		log.info("AuthenticationController");
		
		//Encodage du mot de passe 
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 
		// on recupère l'utilisateur
			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());  
			
			String passwordHashStocked = userDetails.getPassword();
			/* il est impossible de cecripter bcrypt , à la place on utilise matche */
			
			boolean isValid = encoder.matches(request.getPassword(), passwordHashStocked);
		
		if (!isValid) {
			throw new BadCredentialsException(Constants.FAILED);
			
		}
		// on génère le token 
		final String jwt = jwtUtil.generateToken(userDetails);
		log.info(jwt);
		// renvoi du token 
		return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
		
		
		
	}

}

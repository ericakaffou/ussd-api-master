package com.app.ussd.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.ussd.service.auth.ApplicationUserDetailsService;
import com.app.ussd.service.auth.JwtUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ApplicationRequestFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtUtil;
	
	private  final ApplicationUserDetailsService  userDetailsService;


	ApplicationRequestFilter(JwtUtil jwtUtil, ApplicationUserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
   


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		log.info("ApplicationRequestFilter");
		final String authHeader = request.getHeader("Authorization");		
		String username = null;
		String jwt = null;
		String idEntreprise = null;
		
		if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer ")) {			
			jwt = authHeader.substring(7);
			
			//Extracttion du username à partir du token
			username = jwtUtil.extractUsername(jwt);
			//idEntreprise = jwtUtil.extractIdEntreprise(jwt); //(1)
		}
		if (StringUtils.hasLength(username) && SecurityContextHolder.getContext().getAuthentication() == null) {			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(jwtUtil.validateToken(jwt, userDetails)) { 
				//Creation d'un UsernamePasswordAuthenticationToken pour mon user et en le lui donnant via le userDetails de springsecurity
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null,userDetails.getAuthorities()
			);
			
				// ajout des details de la requete
			usernamePasswordAuthenticationToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request)
			);
			//ajouter les details du user au contexte de l'applictaion
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
		}
		//stokage de l'id extrait est (1)..... euhh facultatif si pas de new variables ajoutées
		MDC.put("idEntreprise", idEntreprise);
		chain.doFilter(request, response);
		log.info("Out ApplicationRequestFilter");
		
		
	}

}

package com.app.ussd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.ussd.service.auth.ApplicationUserDetailsService;



@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	private final ApplicationUserDetailsService applicationUserDetailsService;
	
	
	private  final ApplicationRequestFilter applicationRequestFilter;	

	public SecurityConfiguration(ApplicationUserDetailsService applicationUserDetailsService , ApplicationRequestFilter applicationRequestFilter){
        this.applicationUserDetailsService = applicationUserDetailsService;
		this.applicationRequestFilter = applicationRequestFilter ;

	}
	
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	  @Bean
	    public PasswordEncoder noPasswordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }
	
	 @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(applicationUserDetailsService)
			.passwordEncoder(noPasswordEncoder());
			 
		}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // desactivé car appli local
			.authorizeRequests(requests -> requests.antMatchers(
				"/**/**/auth/authenticate",
				"/configuration/ui",
				"/configuration/security",
				"/swagger-ui/**",
				"/swagger-resources/**",
				"/v2/api-docs",
				"/v3/api-docs",
				"/webjars/**",
				"/swagger-ui/index.html",
				"/inscriptionController/**").permitAll()
				.anyRequest().authenticated()).sessionManagement(management -> management
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS));		 
		 //pour activer le flitre ApplicationRequestFilter
	     http.addFilterBefore(applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);
		  
		}
	
		
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}
	

}

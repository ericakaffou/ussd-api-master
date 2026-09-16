package com.app.ussd.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.service.contexts.SecurityContext;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.app.ussd.controller")
public class SwaggerConfiguration implements WebMvcConfigurer {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	  @Bean
	  Docket api() 
	   {
	       return new Docket(DocumentationType.SWAGGER_2)
	    		   .enable(true)
	    		   .apiInfo(new ApiInfoBuilder()
	    				    .description("USSD API documentation")
	    				    .title("USSD REST API")
	    				    .build())
	               .groupName("REST API V2")
	              .securityContexts(Collections.singletonList(securityContext()))
	               .securitySchemes(Collections.singletonList(apiKey()))
	               .useDefaultResponseMessages(false)
	    		   .select()
	               .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
	                // .paths(PathSelectors.regex("/Produits.*")) //permet de passer une expression régulière qui n'accepte que les URI commençant par /Produits.
	               //.paths(PathSelectors.ant(APP_ROOT  +"/**"))
	               .paths(PathSelectors.any())
	               .build();
	   }
	  
  private ApiKey apiKey() {
		  
		  return new ApiKey("JWT" ,AUTHORIZATION_HEADER , "header");
	  }
	  	
	  private SecurityContext securityContext() {
		  
		  return SecurityContext.builder()
				  .securityReferences(defaultAuth())
				  .build();
		  
	  }
	  
	  List<SecurityReference> defaultAuth(){
		  AuthorizationScope authorizationScope  = new AuthorizationScope("global","accessEverything");
		  
		  AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		  authorizationScopes[0] = authorizationScope;
		  return Collections.singletonList(new SecurityReference ("JWT", authorizationScopes ) );
		  
		  
	  }
	  
	  	
	  
	  

}

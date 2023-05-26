package com.spring.veacy.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;



@Configuration
public class MySwaggerConfig {

	 @Bean
	    public OpenAPI api() {
	        
	        Contact contact = new Contact();
	        contact.email("vvigneshwaran0209@gmail.com")
	        .url("https://github.com/vignesh2911/Java-Training")
	        .name("Vigneshwaran");
	        
	        License mitLicense = new License()
	                .name("Pirai License")
	                .url("https://www.piraiinfo.com");
	        
	        Info info = new Info();
	        info.title("Swagger for Scheme and User Scheme Mapping Tables")
	        .description("This is a RestAPI for Scheme Operations")
	        .version("2.0")
	        .contact(contact)
	        .license(mitLicense);
	        
	        Server localServer = new Server();
	        localServer.url("http://localhost:8080");
	        localServer.description("Server URL in Local environment");

	        Server productionServer = new Server();
	        productionServer.setUrl("https");
//	        productionServer.setDescription("Server URL in Production environment");
	        
	        Server devServer = new Server();
	        devServer.url("https://dev.com");
	        devServer.description("Server URL in dev environment");
	        
	        return new OpenAPI().info(info)
	                .servers(List.of(localServer,productionServer,devServer));
	    }
	 
	 
}

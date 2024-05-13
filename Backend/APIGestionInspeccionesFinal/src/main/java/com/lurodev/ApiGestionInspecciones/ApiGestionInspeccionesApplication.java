package com.lurodev.ApiGestionInspecciones;

import com.lurodev.ApiGestionInspecciones.constants.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ApiGestionInspeccionesApplication {
	private Constants constants = new Constants();

	public static void main(String[] args) {
		boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
		String propertySource;

		if (isWindows) {
			propertySource = "classpath:application.properties";
		} else {
			propertySource = "file:./application.properties";
		}

		SpringApplication app = new SpringApplication(ApiGestionInspeccionesApplication.class);
		app.setDefaultProperties(Collections.singletonMap("spring.config.location", propertySource));
		app.run(args);
		//SpringApplication.run(ApiGestionInspeccionesApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(constants.getFrontAllowedOriginTest());
		config.addAllowedOrigin(constants.getFrontAllowedOriginProduction());
		config.addAllowedHeader("*");
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.POST);
		config.addAllowedMethod(HttpMethod.PUT);
		config.addAllowedMethod(HttpMethod.DELETE);
		config.addAllowedMethod(HttpMethod.OPTIONS);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}

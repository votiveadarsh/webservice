package com.theimitation.webservice;

import java.util.Collections;

/**
 * Hello world!
 *
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.theimitation.webservice.model.Product;
import com.theimitation.webservice.service.ProductService;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			productService.save(new Product(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
			productService.save(new Product(2L, "Game Console", 200.00, "http://placehold.it/200x100"));
			productService.save(new Product(3L, "Sofa", 100.00, "http://placehold.it/200x100"));
			productService.save(new Product(4L, "Icecream", 5.00, "http://placehold.it/200x100"));
			productService.save(new Product(5L, "Beer", 3.00, "http://placehold.it/200x100"));
			productService.save(new Product(6L, "Phone", 500.00, "http://placehold.it/200x100"));
			productService.save(new Product(7L, "Watch", 30.00, "http://placehold.it/200x100"));
		};
	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("http://20.198.10.4:8086"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
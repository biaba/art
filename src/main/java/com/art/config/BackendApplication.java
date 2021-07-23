package com.art.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.art.entities.CategoryType;
import com.art.entities.Image;
import com.art.entities.learning.Item;
import com.art.entities.learning.SearchResponse;
import com.art.services.ImageServiceImple;
import com.art.services.UserService;
import com.art.services.UserServiceImple;

@SpringBootApplication
@ComponentScan("com.art")
@EntityScan("com.art.entities")
@EnableJpaRepositories("com.art.repositories")
@EnableWebMvc
public class BackendApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			SearchResponse results = restTemplate.getForObject(
					"https://collectionapi.metmuseum.org/public/collection/v1/search?q=gogh", SearchResponse.class);
			log.info(results.toString());

			int id[] = results.getObjectIDs();

			Item item = restTemplate.getForObject(
					"https://collectionapi.metmuseum.org/public/collection/v1/objects/{id}", Item.class,
					String.valueOf(id[0]));

			log.info(item.toString());

		};
	}

	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BackendApplication.class);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	
// Adding local disk as external source of files
	public static String dict= System.getProperty("user.home") + "\\OneDrive\\Desktop";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + dict+"\\");
    }
}

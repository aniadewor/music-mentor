package com.musicmentor.musicmentor;

import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@EnableJpaRepositories(basePackages = "com.musicmentor.musicmentor.repository")
@SpringBootApplication
@Configuration
@CrossOrigin(origins = "http://localhost:4200")
public class MusicmentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicmentorApplication.class, args);
	}

}


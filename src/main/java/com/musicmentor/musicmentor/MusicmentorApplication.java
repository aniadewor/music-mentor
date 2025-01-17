package com.musicmentor.musicmentor;

import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.musicmentor.musicmentor.repository")
@SpringBootApplication
public class MusicmentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicmentorApplication.class, args);
	}
}

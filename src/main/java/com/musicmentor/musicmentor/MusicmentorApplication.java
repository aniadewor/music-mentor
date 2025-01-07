package com.musicmentor.musicmentor;

import com.musicmentor.musicmentor.model.Role;
import com.musicmentor.musicmentor.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicmentorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicmentorApplication.class, args);
		User user1 = new User();
		user1.setId(1);
		user1.setName("John Doe");
		user1.setEmail("john.doe@gmail.com");
		user1.setPassword("password");
		user1.setRole(Role.STUDENT);
		System.out.println(user1.getEmail());
	}

}

package com.knf.dev.librarymanagementsystem;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.knf.dev.librarymanagementsystem.entity.Author;
import com.knf.dev.librarymanagementsystem.entity.Book;
import com.knf.dev.librarymanagementsystem.entity.Category;
import com.knf.dev.librarymanagementsystem.entity.Publisher;
import com.knf.dev.librarymanagementsystem.entity.Role;
import com.knf.dev.librarymanagementsystem.entity.User;
import com.knf.dev.librarymanagementsystem.repository.UserRepository;
import com.knf.dev.librarymanagementsystem.service.BookService;

@SpringBootApplication
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {
			logger.info("Starting data initialization...");
			try {
				initializeData();
				logger.info("Data initialization completed successfully.");
			} catch (Exception e) {
				logger.error("Error during data initialization: ", e);
			}
		};
	}

	@Transactional
	public void initializeData() {

		if (userRepository.findAll().isEmpty()) {
			logger.info("Creating initial users...");
			var user = new User("admin", "admin", "admin@admin.in", passwordEncoder.encode("Gema123"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			var user1 = new User("hassan", "kamli", "etudiant@gema.edu", passwordEncoder.encode("Gema123"),
					Arrays.asList(new Role("ROLE_USER")));
			userRepository.save(user);
			userRepository.save(user1);
		}
	}
}
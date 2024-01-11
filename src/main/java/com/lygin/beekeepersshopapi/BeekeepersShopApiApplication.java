package com.lygin.beekeepersshopapi;

import com.lygin.beekeepersshopapi.entity.Role;
import com.lygin.beekeepersshopapi.entity.User;
import com.lygin.beekeepersshopapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BeekeepersShopApiApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BeekeepersShopApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(adminAccount == null){
			User user = new User();
			user.setEmail("admin@admin.ru");
			user.setFirstname("Admin");
			user.setLastname("Admin");
			user.setPassword(new BCryptPasswordEncoder().encode("ADMIN"));
			user.setRole(Role.ADMIN);
			userRepository.save(user);
		}

	}
}

package com.restaurant.backend;

import com.restaurant.backend.model.*;
import com.restaurant.backend.repository.RoleRepository;
import com.restaurant.backend.service.CategoryService;
import com.restaurant.backend.service.FoodService;
import com.restaurant.backend.service.UserService;
import com.restaurant.backend.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@SpringBootApplication
public class BackendApplication  implements CommandLineRunner {
	private final UserService userService;
	private final CategoryService categoryService;
	private final FoodService foodService;
	private final RoleRepository roleRepository;

	@Autowired
	public BackendApplication(UserService userService, CategoryService categoryService, FoodService foodService, RoleRepository roleRepository) {
		this.userService = userService;
		this.categoryService = categoryService;
		this.foodService = foodService;
		this.roleRepository = roleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role role = new Role();

		if (roleRepository.findOne(1) == null){
			role.setRoleId(1);
			role.setName("ROLE_USER");
			roleRepository.save(role);
		}

		if (roleRepository.findOne(0) == null){
			role.setRoleId(0);
			role.setName("ROLE_ADMIN");
			roleRepository.save(role);
		}

		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Adams");
		user1.setPassword("p");
		user1.setBirthday(new Date());
		user1.setCompany("new company");
		user1.setFax("012-345-6789");
		user1.setPhone("999-999-9999");
		user1.setEmail("JAdams@gmail.com");
		user1.setUsername("jadams");

		userService.createUser(user1);

		User user2 = new User();
		user2.setFirstName("Admin");
		user2.setLastName("Admin");
		user2.setPassword("password");
		user2.setEmail("Admin@gmail.com");
		user2.setBirthday(new Date());
		user2.setCompany("new company");
		user2.setFax("012-345-6789");
		user2.setPhone("999-999-9999");
		user2.setUsername("admin");
		Role role2 = new Role();
		role2.setRoleId(0);
		role2.setName("ROLE_ADMIN");
		user2.getUserRoles().add(new UserRole(user2, role2));
		userService.createUser(user2);

		List<Category> categories = new ArrayList<>();
		categories.add(new Category("appetizer"));
		categories.add(new Category("seafood"));
		categories.add(new Category("pork"));
		categories.add(new Category("beef"));
		categories.add(new Category("chicken"));

		for (Category category : categories) {
			categoryService.addCategory(category);
		}

		Food chilledJellyFish = new Food("Chilled Jelly Fish", categoryService.getCategoryByName("appetizer"), new BigDecimal(11.95).setScale(2, RoundingMode.CEILING), "chilled jelly fish");
		Food threeCupChicken = new Food("Three Cup Chicken", categoryService.getCategoryByName("chicken"), new BigDecimal(12.95).setScale(2, RoundingMode.CEILING), "chicken in three cups");
		Food kungPaoShrimp = new Food("Kung Pao Shrimp", categoryService.getCategoryByName("seafood"), new BigDecimal(13.95).setScale(2, RoundingMode.CEILING), "shrimp in kung pao style");
		Food cuminBeef = new Food("Cumin Beef", categoryService.getCategoryByName("beef"), new BigDecimal(15.95).setScale(2, RoundingMode.CEILING), "beef stir fried with cumin");
		Food malaSpicyBeefTendon = new Food("Mala Spicy Beef Tendon", categoryService.getCategoryByName("Beef"), new BigDecimal(15.95).setScale(2, RoundingMode.CEILING), "beef tendon with mala source");
		Food porkSteak = new Food("Pork Steak", categoryService.getCategoryByName("pork"), new BigDecimal(13.99).setScale(2, RoundingMode.CEILING), "fully cooked pork steak");
		List<Food> foodList = new ArrayList<>(Arrays.asList(chilledJellyFish, threeCupChicken,kungPaoShrimp, cuminBeef, malaSpicyBeefTendon, porkSteak ));

		for (Food food : foodList) {
			foodService.addFood(food);
		}
	}

}

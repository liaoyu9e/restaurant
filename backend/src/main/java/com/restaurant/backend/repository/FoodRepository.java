package com.restaurant.backend.repository;

import com.restaurant.backend.model.Category;
import com.restaurant.backend.model.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Long> {
    List<Food> findByCategory(Category category);

    Food findByName(String name);
}

package com.restaurant.backend.service;

import com.restaurant.backend.model.Category;
import com.restaurant.backend.model.Food;

import java.util.List;

public interface FoodService {
    List<Food> getFoodListByCategory(Category category);

    Food addFood(Food food);

    Food getFoodByName(String name);

    Food getFoodById(Long id);
}

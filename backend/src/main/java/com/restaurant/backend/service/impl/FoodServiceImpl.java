package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.Category;
import com.restaurant.backend.model.Food;
import com.restaurant.backend.model.User;
import com.restaurant.backend.repository.FoodRepository;
import com.restaurant.backend.service.FoodService;
import com.restaurant.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FoodServiceImpl implements FoodService {
    private static final Logger LOG = LoggerFactory.getLogger(Food.class);


    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getFoodListByCategory(Category category) {
        return foodRepository.findByCategory(category);
    }

    @Override
    public Food addFood(Food food) {
        Food localFood = foodRepository.findByName(food.getName());

        if(localFood != null) {
            LOG.info("Food with name {} already exist. Nothing will be done. ", food.getName());
        } else {
            localFood = foodRepository.save(food);
        }

        return localFood;
    }

    @Override
    public Food getFoodByName(String name) {
        return foodRepository.findByName(name);
    }

    @Override
    public Food getFoodById(Long id) {
        return foodRepository.findOne(id);
    }
}

package com.restaurant.backend.controller;

import com.restaurant.backend.model.Food;
import com.restaurant.backend.service.CategoryService;
import com.restaurant.backend.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{category}", method = RequestMethod.GET)
    public List<Food> getFoodListByCategory(@PathVariable String categoryName){
        return foodService.getFoodListByCategory(categoryService.getCategoryByName(categoryName));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Food addFood(@RequestBody Food food){
        return foodService.addFood(food);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Food getFoodById(@PathVariable String id) {
        return foodService.getFoodById(Long.parseLong(id));
    }
}

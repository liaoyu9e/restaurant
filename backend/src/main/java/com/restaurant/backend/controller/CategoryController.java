package com.restaurant.backend.controller;


import com.restaurant.backend.model.Category;
import com.restaurant.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService=categoryService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Category> getCategoryList() {
        return categoryService.getCategoryList();
    }
}

package com.restaurant.backend.service;

import com.restaurant.backend.model.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    Category getCategoryByName(String name);

    List<Category> getCategoryList();
}

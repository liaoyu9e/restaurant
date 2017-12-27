package com.restaurant.backend.service;

import com.restaurant.backend.model.Category;

public interface CategoryService {
    Category addCategory(Category category);

    Category getCategoryByName(String name);
}

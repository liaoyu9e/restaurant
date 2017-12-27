package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.Category;
import com.restaurant.backend.model.User;
import com.restaurant.backend.repository.CategoryRepository;
import com.restaurant.backend.service.CategoryService;
import com.restaurant.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(Category.class);
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository=categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        Category localCategory = categoryRepository.findByName(category.getName());

        if(localCategory != null) {
            LOG.info("Category with name {} already exist. Nothing will be done. ", category.getName());
        } else {
            localCategory = categoryRepository.save(localCategory);
        }

        return localCategory;
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getCategoryList() {
        return (List<Category>) categoryRepository.findAll();
    }
}

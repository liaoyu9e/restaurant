package com.restaurant.backend.repository;

import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.Food;
import com.restaurant.backend.model.FoodToCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodToCartRepository extends CrudRepository<FoodToCart, Long> {
    List<FoodToCart> findByCart(Cart cart);

    FoodToCart findByFood(Food food);
}

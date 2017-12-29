package com.restaurant.backend.service;

import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.Food;
import com.restaurant.backend.model.FoodToCart;
import com.restaurant.backend.model.User;

import java.util.List;

public interface CartService {
    Cart createCart(Cart cart);

    List<FoodToCart> getFoodToCartListByCart(Cart cart);

    Cart getCartByUser(User user);

    Cart getCartByCartId(Long id);

    FoodToCart addFoodToCart(Food food, Cart cart, int qty);

    Cart refreshCart(Cart cart);

    List<FoodToCart> updateFoodQty(FoodToCart foodToCart);

    List<FoodToCart> removeFoodFromCart(Long id);
}

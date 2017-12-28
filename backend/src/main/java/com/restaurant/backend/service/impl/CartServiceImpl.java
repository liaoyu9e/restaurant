package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.Food;
import com.restaurant.backend.model.FoodToCart;
import com.restaurant.backend.model.User;
import com.restaurant.backend.repository.CartRepository;
import com.restaurant.backend.repository.FoodToCartRepository;
import com.restaurant.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final FoodToCartRepository foodToCartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, FoodToCartRepository foodToCartRepository) {
        this.cartRepository = cartRepository;
        this.foodToCartRepository = foodToCartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<FoodToCart> getFoodToCartListByCart(Cart cart) {
        return foodToCartRepository.findByCart(cart);
    }

    @Override
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public FoodToCart addFoodToCart(Food food, Cart cart, int qty) {
        FoodToCart cartItem = new FoodToCart();
        cartItem.setCart(cart);
        cartItem.setFood(food);
        cartItem.setQuantity(qty);
        cartItem.setSubtotal(food.getPrice().multiply(new BigDecimal(qty)));

        FoodToCart localCartItem = foodToCartRepository.save(cartItem);

        refreshCart(cart);

        return localCartItem;
    }

    @Override
    public Cart refreshCart(Cart cart) {
        List<FoodToCart> cartItemList = foodToCartRepository.findByCart(cart);

        BigDecimal total = new BigDecimal(0);

        for (FoodToCart cartItem : cartItemList) {
            total=total.add(cartItem.getSubtotal());
        }

        cart.setTotal(total);
        cartRepository.save(cart);

        return cart;
    }
}

package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.Food;
import com.restaurant.backend.model.FoodToCart;
import com.restaurant.backend.model.User;
import com.restaurant.backend.repository.CartRepository;
import com.restaurant.backend.repository.FoodRepository;
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
    private final FoodRepository foodRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, FoodToCartRepository foodToCartRepository, FoodRepository
            foodRepository) {
        this.cartRepository = cartRepository;
        this.foodToCartRepository = foodToCartRepository;
        this.foodRepository = foodRepository;
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
    public Cart getCartByCartId(Long id) {
        return cartRepository.findOne(id);
    }

    @Override
    public FoodToCart addFoodToCart(Food food, Cart cart, int qty) {

        List<FoodToCart> foodToCartList = foodToCartRepository.findByCart(cart);

        FoodToCart localCartItem = null;

        for (FoodToCart cartItem : foodToCartList) {
            if (cartItem.getFood().getId().equals(food.getId())) {
                localCartItem=cartItem;
                break;
            }
        }

        if (localCartItem!=null) {

            int newQty = localCartItem.getQty()+qty;
            localCartItem.setQty(newQty);
            localCartItem.setSubtotal(localCartItem.getFood().getPrice().multiply(new BigDecimal(newQty)));

            localCartItem = foodToCartRepository.save(localCartItem);

        } else {

            FoodToCart cartItem = new FoodToCart();
            cartItem.setCart(cart);
            cartItem.setFood(food);
            cartItem.setQty(qty);
            cartItem.setSubtotal(food.getPrice().multiply(new BigDecimal(qty)));

            localCartItem = foodToCartRepository.save(cartItem);

        }

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

    @Override
    public List<FoodToCart> updateFoodQty(FoodToCart foodToCart) {
        FoodToCart localFoodToCart = foodToCartRepository.findOne(foodToCart.getId());
        localFoodToCart.setQty(foodToCart.getQty());
        localFoodToCart.setSubtotal(localFoodToCart.getFood().getPrice().multiply(new BigDecimal(localFoodToCart.getQty())));
        foodToCartRepository.save(localFoodToCart);
        Cart cart = localFoodToCart.getCart();
        refreshCart(cart);

        return cart.getFoodToCartList();
    }

    @Override
    public List<FoodToCart> removeFoodFromCart(Long id) {
        FoodToCart foodToCart = foodToCartRepository.findOne(id);
        Cart cart = foodToCart.getCart();
        foodToCartRepository.delete(id);

        refreshCart(cart);

        return cart.getFoodToCartList();
    }
}

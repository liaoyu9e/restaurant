package com.restaurant.backend.controller;

import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.Food;
import com.restaurant.backend.model.FoodToCart;
import com.restaurant.backend.model.User;
import com.restaurant.backend.service.CartService;
import com.restaurant.backend.service.FoodService;
import com.restaurant.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final FoodService foodService;

    @Autowired
    public CartController(CartService cartService, UserService userService, FoodService foodService) {
        this.cartService = cartService;
        this.userService = userService;
        this.foodService = foodService;
    }

    @RequestMapping(value = "/foodList", method = RequestMethod.GET)
    public List<FoodToCart> getFoodListByCart(Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        Cart cart = cartService.getCartByUser(user);

        return cartService.getFoodToCartListByCart(cart);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public FoodToCart addFoodToCart(@RequestBody HashMap<String, String> mapper,
                              Principal principal) {
        String foodId =  mapper.get("foodId");
        String qty =  mapper.get("qty");

        Food food = foodService.getFoodById(Long.parseLong(foodId));

        User user = userService.getUserByUsername(principal.getName());
        Cart cart = cartService.getCartByUser(user);

        return cartService.addFoodToCart(food, cart, Integer.parseInt(qty));
    }

    @RequestMapping(value = "/updateQty", method = RequestMethod.POST)
    public List<FoodToCart> updateFoodQty(@RequestBody FoodToCart foodToCart) {
        return cartService.updateFoodQty(foodToCart);
    }

    @RequestMapping(value = "/{foodToCartId}", method = RequestMethod.DELETE)
    public  List<FoodToCart> removeFoodFromCart(@PathVariable String foodToCartId) {
        return cartService.removeFoodFromCart(Long.parseLong(foodToCartId));
    }
}

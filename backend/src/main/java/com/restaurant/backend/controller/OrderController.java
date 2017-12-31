package com.restaurant.backend.controller;


import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.CustomerOrder;
import com.restaurant.backend.model.User;
import com.restaurant.backend.service.CartService;
import com.restaurant.backend.service.OrderService;
import com.restaurant.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public CustomerOrder createOrder(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        Cart cart = cartService.getCartByUser(user);

        return orderService.createOrder(cart);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CustomerOrder> getOrderListByUser(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());

        return orderService.getOrderListByUser(user);
    }


}

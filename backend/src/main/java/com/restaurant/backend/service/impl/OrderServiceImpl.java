package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.*;
import com.restaurant.backend.repository.FoodToCartRepository;
import com.restaurant.backend.repository.FoodToOrderRepository;
import com.restaurant.backend.repository.OrderRepository;
import com.restaurant.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodToCartRepository foodToCartRepository;

    @Autowired
    private FoodToOrderRepository foodToOrderRepository;

    @Override
    public CustomerOrder createOrder(Cart cart) {
        List<FoodToCart> foodToCartList = foodToCartRepository.findByCart(cart);

        CustomerOrder order = new CustomerOrder();

        List<FoodToOrder> foodToOrderList = new ArrayList<>();

        for (FoodToCart foodToCart : foodToCartList) {
            FoodToOrder foodToOrder = new FoodToOrder(foodToCart.getFood(), foodToCart.getQty(), foodToCart.getSubtotal());
            foodToOrder = foodToOrderRepository.save(foodToOrder);
            foodToOrderList.add(foodToOrder);
        }

        order.setFoodToOrderList(foodToOrderList);
        order.setStatus("Submitted");

        if(null != cart.getUser()) {
            order.setUser(cart.getUser());
        }

        if(null != cart.getGuest()) {
            order.setGuest(cart.getGuest());
        }

        if (null == cart.getUser() && null == cart.getGuest()) {
            throw new DuplicateKeyException("This cart is found for both an User and a Guest");
        }

        order.setTotal(cart.getTotal());

        order.setCreationDate(new Date());

        order = orderRepository.save(order);

        for (FoodToOrder foodToOrder : foodToOrderList) {
            foodToOrder.setOrder(order);
            foodToOrderRepository.save(foodToOrder);
        }

        cart.setTotal(new BigDecimal(0));
        foodToCartRepository.delete(foodToCartList);

        return order;
    }

    @Override
    public List<CustomerOrder> getOrderListByUser(User user) {
        return orderRepository.findByUser(user);
    }
}

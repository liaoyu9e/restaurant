package com.restaurant.backend.service;

import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.CustomerOrder;
import com.restaurant.backend.model.OrderInfo;
import com.restaurant.backend.model.User;

import java.util.List;

public interface OrderService {

    CustomerOrder createOrder(OrderInfo orderInfo, Cart cart);

    List<CustomerOrder> getOrderListByUser(User user);

}

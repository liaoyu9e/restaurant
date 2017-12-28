package com.restaurant.backend.repository;

import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByUser(User user);
}

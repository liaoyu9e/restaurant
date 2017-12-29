package com.restaurant.backend.repository;

import com.restaurant.backend.model.CustomerOrder;
import com.restaurant.backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<CustomerOrder, Long>{

    List<CustomerOrder> findByUser(User user);
}

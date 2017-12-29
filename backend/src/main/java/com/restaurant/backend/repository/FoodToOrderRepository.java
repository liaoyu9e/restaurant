package com.restaurant.backend.repository;

import com.restaurant.backend.model.FoodToOrder;
import org.springframework.data.repository.CrudRepository;

public interface FoodToOrderRepository extends CrudRepository<FoodToOrder, Long> {
}

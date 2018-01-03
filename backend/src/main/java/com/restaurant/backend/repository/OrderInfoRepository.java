package com.restaurant.backend.repository;

import com.restaurant.backend.model.OrderInfo;
import org.springframework.data.repository.CrudRepository;

public interface OrderInfoRepository extends CrudRepository<OrderInfo, Long>{
}

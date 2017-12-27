package com.restaurant.backend.repository;

import com.restaurant.backend.model.Address;
import com.restaurant.backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByUser(User user);
}

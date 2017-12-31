package com.restaurant.backend.repository;

import com.restaurant.backend.model.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {

    Guest findByEmail(String email);
}

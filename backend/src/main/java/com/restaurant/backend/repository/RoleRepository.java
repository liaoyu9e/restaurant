package com.restaurant.backend.repository;


import com.restaurant.backend.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}

package com.restaurant.backend.repository;

import com.restaurant.backend.model.CreditCard;
import com.restaurant.backend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
    List<CreditCard> findByUser(User user);

}

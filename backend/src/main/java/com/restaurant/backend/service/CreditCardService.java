package com.restaurant.backend.service;

import com.restaurant.backend.model.CreditCard;
import com.restaurant.backend.model.User;

import java.util.List;

public interface CreditCardService {
    CreditCard addCreditCard(CreditCard creditCard);

    List<CreditCard> getCreditCardList(User user);

    CreditCard getCreditCardById(Long id);

    CreditCard updateCreditCard(CreditCard creditCard);

    void removeCreditCard(CreditCard creditCard);
}

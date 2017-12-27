package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.CreditCard;
import com.restaurant.backend.model.User;
import com.restaurant.backend.repository.CreditCardRepository;
import com.restaurant.backend.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public CreditCard addCreditCard(CreditCard creditCard) {

        return creditCardRepository.save(creditCard);
    }

    @Override
    public List<CreditCard> getCreditCardList(User user) {
        return creditCardRepository.findByUser(user);
    }

    @Override
    public CreditCard getCreditCardById(Long id) {
        return creditCardRepository.findOne(id);
    }

    @Override
    public CreditCard updateCreditCard(CreditCard creditCard) {
        CreditCard localCreditCard = creditCardRepository.findOne(creditCard.getId());
        creditCard.setUser(localCreditCard.getUser());

        return creditCardRepository.save(creditCard);
    }

    @Override
    public void removeCreditCard(CreditCard creditCard) {
        creditCardRepository.delete(creditCard);
    }
}

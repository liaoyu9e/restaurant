package com.restaurant.backend.controller;

import com.restaurant.backend.model.CreditCard;
import com.restaurant.backend.model.User;
import com.restaurant.backend.service.CreditCardService;
import com.restaurant.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/creditCard")
public class CreditCardController {

    private final CreditCardService creditCardService;

    private final UserService userService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService, UserService userService) {
        this.creditCardService = creditCardService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public CreditCard addCreditCard(@RequestBody CreditCard creditCard, Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        creditCard.setUser(user);
        return creditCardService.addCreditCard(creditCard);
    }

    @RequestMapping("/list")
    public List<CreditCard> getCreditCardListByUser(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        return creditCardService.getCreditCardList(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CreditCard updateCreditCard(@RequestBody CreditCard creditCard) {

        return creditCardService.updateCreditCard(creditCard);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeCreditCard(@PathVariable String id) {
        CreditCard creditCard = creditCardService.getCreditCardById(Long.parseLong(id));

        creditCardService.removeCreditCard(creditCard);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CreditCard getCreditCardById(@PathVariable String id) {
        return creditCardService.getCreditCardById(Long.parseLong(id));
    }
}

package com.restaurant.backend.controller;

import com.restaurant.backend.model.Guest;
import com.restaurant.backend.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuestController {
    private final GuestService guestService;


    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Guest addGuest(@RequestBody Guest guest) {
        return guestService.addGuest(guest);
    }

}

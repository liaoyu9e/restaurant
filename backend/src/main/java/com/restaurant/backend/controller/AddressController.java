package com.restaurant.backend.controller;


import com.restaurant.backend.model.Address;
import com.restaurant.backend.model.User;
import com.restaurant.backend.service.AddressService;
import com.restaurant.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    private final UserService userService;

    @Autowired
    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Address addAddress(@RequestBody Address address, Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        address.setUser(user);
        return addressService.addAddress(address);
    }

    @RequestMapping("/list")
    public List<Address> getAddressListByUser(Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        return addressService.getAddressList(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Address updateAddress(@RequestBody Address address) {

        return addressService.updateAddress(address);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeAddress(@PathVariable String id) {
        Address address = addressService.getAddressById(Long.parseLong(id));

        addressService.removeAddress(address);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Address getAddressById(@PathVariable String id) {
        return addressService.getAddressById(Long.parseLong(id));
    }
}

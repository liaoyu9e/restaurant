package com.restaurant.backend.service;

import com.restaurant.backend.model.Address;
import com.restaurant.backend.model.User;

import java.util.List;

public interface AddressService {
    Address addAddress(Address address);

    List<Address> getAddressList(User user);

    Address getAddressById(Long id);

    Address updateAddress(Address address);

    void removeAddress(Address address);
}

package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.Address;
import com.restaurant.backend.model.User;
import com.restaurant.backend.repository.AddressRepository;
import com.restaurant.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address addAddress(Address address) {

        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressList(User user) {
        return addressRepository.findByUser(user);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findOne(id);
    }

    @Override
    public Address updateAddress(Address address) {
        Address localAddress = addressRepository.findOne(address.getId());
        address.setUser(localAddress.getUser());

        return addressRepository.save(address);
    }

    @Override
    public void removeAddress(Address address) {
        addressRepository.delete(address);
    }
}

package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.*;
import com.restaurant.backend.repository.CartRepository;
import com.restaurant.backend.repository.GuestRepository;
import com.restaurant.backend.repository.UserRepository;
import com.restaurant.backend.service.GuestService;
import com.restaurant.backend.service.UserService;
import com.restaurant.backend.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.restaurant.backend.utility.SecurityUtility.randomPassword;

@Service

public class GuestServiceImpl implements GuestService{

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final GuestRepository guestRepository;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.guestRepository = guestRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Guest addGuest(Guest guest) {
        User user = userRepository.findByEmail(guest.getEmail());
        Guest localGuest = guestRepository.findByEmail(guest.getEmail());

        if(user != null) {

            LOG.warn("This email {} has already been registered with an account.", guest.getEmail());

        } else {

            if (localGuest != null) {
                LOG.info("Guest with email {} found. No creation needed.", guest.getEmail());
                localGuest.setFirstName(guest.getFirstName());
                localGuest.setLastName(guest.getLastName());
                localGuest.setDate(new Date());
                localGuest.setUsername(guest.getEmail());
                localGuest.setEmail(guest.getEmail());

                String password = randomPassword();
                localGuest.setTempPassword(password);
                String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
                localGuest.setPassword(encryptedPassword);
                guestRepository.save(localGuest);

            } else {

                localGuest = new Guest();

                Set<GuestRole> guestRoles = new HashSet<>();
                Role localRole = new Role();
                localRole.setRoleId(2);
                guestRoles.add(new GuestRole(localGuest, localRole));
                localGuest.getGuestRoles().addAll(guestRoles);

                String password = randomPassword();
                localGuest.setTempPassword(password);
                String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
                localGuest.setPassword(encryptedPassword);
                localGuest.setUsername(guest.getEmail());
                localGuest.setFirstName(guest.getFirstName());
                localGuest.setLastName(guest.getLastName());
                localGuest.setDate(new Date());
                localGuest.setEmail(guest.getEmail());

                Cart cart = new Cart();
                cart.setTotal(new BigDecimal(0));
                cart = cartRepository.save(cart);
                localGuest.setCart(cart);

                localGuest = guestRepository.save(localGuest);
                cart.setGuest(localGuest);
                cartRepository.save(cart);
            }
        }

        return localGuest;
    }
}

package com.restaurant.backend.service.impl;

import com.restaurant.backend.model.Cart;
import com.restaurant.backend.model.Role;
import com.restaurant.backend.model.User;
import com.restaurant.backend.model.UserRole;
import com.restaurant.backend.repository.CartRepository;
import com.restaurant.backend.repository.UserRepository;
import com.restaurant.backend.service.CartService;
import com.restaurant.backend.service.UserService;
import com.restaurant.backend.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    private BCryptPasswordEncoder passwordEncoder(){
        return SecurityUtility.passwordEncoder();
    }

    @Override
    public User createUser(User user) {
        User localUser = userRepository.findByEmail(user.getEmail());

        if(localUser != null) {
            LOG.info("User with user email {} already exist. Nothing will be done. ", user.getEmail());
        } else {

            Set<UserRole> userRoles = new HashSet<>();
            Role localRole = new Role();
            localRole.setRoleId(1);
            userRoles.add(new UserRole(user, localRole));
            user.getUserRoles().addAll(userRoles);

            Date today = new Date();
            user.setJoinDate(today);

            String encryptedPassword = SecurityUtility.passwordEncoder().encode(user.getPassword());
            user.setPassword(encryptedPassword);
            user.setUsername(user.getEmail());

            Cart cart = new Cart();
            cart.setTotal(new BigDecimal(0));
            cart = cartRepository.save(cart);
            user.setCart(cart);

            localUser = userRepository.save(user);
            cart.setUser(localUser);
            cartRepository.save(cart);
        }

        return localUser;
    }


    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(User user, User localUser) {
        localUser.setPhone(user.getPhone());
        localUser.setFax(user.getFax());
        localUser.setCompany(user.getCompany());
        localUser.setFirstName(user.getFirstName());
        localUser.setLastName(user.getLastName());

        return userRepository.save(localUser);
    }

    @Override
    public User updatePassword(User user, String currentPassword, String newPassword) {
        BCryptPasswordEncoder encoder = passwordEncoder();

        if(encoder.matches(currentPassword, user.getPassword())) {

            String encryptedPassword = SecurityUtility.passwordEncoder().encode(newPassword);
            user.setPassword(encryptedPassword);
        } else {
            System.out.println("should throw exception");
            throw new AccessDeniedException("Current password is incorrect.");
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }
}

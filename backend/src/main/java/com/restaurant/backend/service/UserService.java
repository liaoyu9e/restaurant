package com.restaurant.backend.service;


import com.restaurant.backend.model.User;

import java.security.Principal;

public interface UserService {
    User createUser(User user);


    User getUserByEmail(String email);

    User getUserByUsername(String username);

    User updateUser(User user, User localUser);

    User updatePassword(User user, String currentPassword, String newPassword);

    User getUserById(Long id);
}

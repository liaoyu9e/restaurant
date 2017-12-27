package com.restaurant.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.backend.model.User;
import com.restaurant.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public User createUser (@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping("/")
    public User getUser(Principal principal) {
        String username = principal.getName();

        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user, Principal principal) {
        String username = principal.getName();
        User localUser = userService.getUserByUsername(username);

        return userService.updateUser(user, localUser);
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public User updatePassword(@RequestBody HashMap<String, String> mapper, Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        ObjectMapper om = new ObjectMapper();

        String currentPassword = om.convertValue(mapper.get("currentPassword"), String.class);

        String newPassword = om.convertValue(mapper.get("newPassword"), String.class);


        return userService.updatePassword(user, currentPassword, newPassword);
    }
}


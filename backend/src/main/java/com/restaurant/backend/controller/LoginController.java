package com.restaurant.backend.controller;

import com.restaurant.backend.model.User;
import com.restaurant.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

//    @RequestMapping("/login")
//    public ResponseEntity login(
//            @RequestParam(value="error", required = false) String error
//    ) {
//        if (error != null) {
//            return new ResponseEntity("Login failed.",HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity("Login success.",HttpStatus.OK);
//    }

//    @RequestMapping("/")
//    public ResponseEntity logout(
//            @RequestParam("logout") String logout
//    ){
//        return new ResponseEntity("Logout success.", HttpStatus.OK);
//    }

//    @RequestMapping(value="login", method = RequestMethod.POST)
//    public String loginPost(
//            @RequestBody Map<String, String> json,
//            HttpServletRequest request
//    ) throws
//            ServletException {
//        if(json.get("email") == null || json.get("password") ==null) {
//            throw new ServletException("Please fill in email and password");
//        }
//
//        String email = json.get("email");
//        String password = json.get("password");
//
//        User user= userService.getUserByEmail(email);
//        if (user==null) {
//            throw new ServletException("Email not found.");
//        }
//
//        String pwd = user.getPassword();
//
//        if(!password.equals(pwd)) {
//            throw new ServletException("Invalid login. Please check your email and password");
//        }
//
//        return "login success";
//    }

    @RequestMapping("/logoutSuccess")
    public String logout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return "logout success";
    }

//    @RequestMapping(value = "/invalidateSession", method = RequestMethod.POST)
//    public String logout() {
//        SecurityContextHolder.clearContext();
//
//        return "logout success.";
//    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/checkSession")
    public String checkSession() {
        return "Session Active";
    }

//    @RequestMapping("/token")
//    @ResponseBody
//    public Map<String,String> token(HttpSession session, HttpServletRequest request) {
//        System.out.println(request.getRemoteHost());
//
//        String remoteHost = request.getRemoteHost();
//        int portNumber = request.getRemotePort();
//
//        System.out.println(remoteHost+":"+portNumber);
//        System.out.println(request.getRemoteAddr());
//
//
//
//        return Collections.singletonMap("token", session.getId());
//    }
}

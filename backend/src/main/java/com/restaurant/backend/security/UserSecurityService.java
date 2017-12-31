package com.restaurant.backend.security;

import com.restaurant.backend.model.Guest;
import com.restaurant.backend.model.User;
import com.restaurant.backend.repository.GuestRepository;
import com.restaurant.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    private final UserRepository userRepository;

    private final GuestRepository guestRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository, GuestRepository guestRepository) {
        this.userRepository = userRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        Guest guest = guestRepository.findByEmail(email);
        if (null == user && null == guest) {
            LOG.warn("Neither User nor Guest with email {} was found", email);
            throw new UsernameNotFoundException("Email " + email + " not found");
        }

        if (null != user && null != guest) {
            LOG.warn("Email {} exists in both user and guest.", email);
            throw new DuplicateKeyException("Duplicate records found in user and guest for email " + email);
        }

        if(null != user) {
            return user;
        } else {
            return guest;
        }
    }
}

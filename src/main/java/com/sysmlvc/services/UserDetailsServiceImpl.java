package com.sysmlvc.services;

/**
 * Created by Jason Han on 7/6/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.sysmlvc.domains.nodes.User;
import com.sysmlvc.repositories.UserRepository;
import com.sysmlvc.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new UserDetailsImpl(user);
        }
    }

    @Transactional
    User register(String email, String password, String firstName, String lastName) {
        return new User();
    }

}
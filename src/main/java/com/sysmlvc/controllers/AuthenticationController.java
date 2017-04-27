package com.sysmlvc.controllers;

/**
 * Created by Jason Han on 6/10/16.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sysmlvc.domains.User;
import com.sysmlvc.repositories.UserRepository;
import com.sysmlvc.security.JwtAuthenticationResponse;
import com.sysmlvc.security.JwtAuthenticationRequest;
import com.sysmlvc.utils.JwtTokenUtil;
import com.sysmlvc.utils.MailUtil;
import com.sysmlvc.security.UserDetailsImpl;
import com.sysmlvc.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController extends BaseController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private MailUtil mailUtil;
    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtTokenUtil jwtTokenUtil, MailUtil mailUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.mailUtil = mailUtil;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        Map<String, Object> response = new HashMap<>();

        User existing = userRepository.findByEmail(user.getEmail());

        if (existing != null) {
            response.put("message", "User exists");
            response.put("data", existing);
            return ResponseEntity.ok(response);
        }

        user.encodePassword(user.getPassword());

        List<User.SecurityRole> roles = new ArrayList<>();
        roles.add(User.SecurityRole.ROLE_USER);
        user.setRoles(roles);

        userRepository.save(user);

        response.put("data", user);
        response.put("message", "User created");
        mailUtil.sendEmail("activate", user.getEmail(), new HashMap<>());

        return ResponseEntity.ok(response);
    }
}
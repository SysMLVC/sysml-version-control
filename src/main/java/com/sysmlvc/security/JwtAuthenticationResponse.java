package com.sysmlvc.security;

/**
 * Created by Jason Han on 6/10/16.
 */

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 7807174184779512457L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
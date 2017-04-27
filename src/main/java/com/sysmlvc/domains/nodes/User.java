package com.sysmlvc.domains.nodes;

import com.sysmlvc.domains.base.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.neo4j.ogm.annotation.typeconversion.Convert;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sysmlvc.converters.UserRolesConverter;

/**
 * Created by Jason Han on 12/15/16.
 */

public class User extends Node {

    private String username;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean enabled;
    private boolean admin;

    @Convert(UserRolesConverter.class)
    private SecurityRole[] roles;

    private Set<Organization> organizations;
    private Set<Project> projects;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public SecurityRole[] getRoles() {
        return roles;
    }

    public void setRoles(List<SecurityRole> roles) {
        this.roles = roles.toArray(new SecurityRole[roles.size()]);
    }

    public boolean getAdmin() {
        if (admin) {
            List<SecurityRole> securityRolesList = new ArrayList<>();
            securityRolesList.add(SecurityRole.ROLE_ADMIN);
            setRoles(securityRolesList);
        }
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void encodePassword(String password) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        this.password = bcrypt.encode(password);
    }

    public void updatePassword(String old, String newPass1, String newPass2) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        if (!password.equals(bcrypt.encode(old))) {
            throw new IllegalArgumentException("Existing Password invalid");
        }
        if (!newPass1.equals(newPass2)) {
            throw new IllegalArgumentException("New Passwords don't match");
        }
        this.password = bcrypt.encode(newPass1);
    }

    public enum SecurityRole implements GrantedAuthority {
        ROLE_USER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }
}

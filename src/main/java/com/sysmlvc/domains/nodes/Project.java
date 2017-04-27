package com.sysmlvc.domains.nodes;

import com.sysmlvc.domains.base.Node;

import java.util.Set;

/**
 * Created by Jason Han on 1/23/17.
 */

public class Project extends Node {

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
